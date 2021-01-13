package com.voyageone.retail.common.generator.mybatis.plugin;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExtModelGeneratorPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze,
                                   TopLevelClass topLevelClass,
                                   IntrospectedTable introspectedTable) {

        GeneratedJavaFile gjf = new GeneratedJavaFile(interfaze,
                context.getJavaClientGeneratorConfiguration().getTargetProject(),
                context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING),
                context.getJavaFormatter());

        return !Utils.checkFileExist(gjf);
    }


    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        return topLevelClass.getType().getFullyQualifiedName().endsWith("_Field");
    }


    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> answer = new ArrayList<>();
        List<CompilationUnit> compilationUnits = getExtCompilationUnits(introspectedTable,introspectedTable.getBaseRecordType() + "_Field", null);
        for (CompilationUnit compilationUnit : compilationUnits) {
            GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
                    context.getJavaModelGeneratorConfiguration().getTargetProject(),
                    context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING),
                    context.getJavaFormatter());
            if (!Utils.checkFileExist(gjf)) {
                answer.add(gjf);
            }
        }

        introspectedTable.setBaseRecordType(introspectedTable.getBaseRecordType() + "_Field");
        compilationUnits = introspectedTable.getGeneratedJavaFiles().stream().map(GeneratedJavaFile::getCompilationUnit).collect(Collectors.toList());
        for (CompilationUnit compilationUnit : compilationUnits) {
            if (compilationUnit instanceof InnerClass) {
                //类文件
                ((InnerClass)compilationUnit).setAbstract(true);
                //((InnerClass)compilationUnit).setVisibility(JavaVisibility.DEFAULT);
                List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
                if (primaryKeyColumns != null && primaryKeyColumns.size() == 1) {
                    String idClassShortName = primaryKeyColumns.get(0).getFullyQualifiedJavaType().getShortName();
                    String superClass = compilationUnit.getSuperClass().getFullyQualifiedName() + String.format("<%s>", idClassShortName);
                    ((InnerClass) compilationUnit).setSuperClass(superClass);
                }
            }else if (compilationUnit instanceof Interface){
                //接口文件
                String modelName = introspectedTable.getFullyQualifiedTable().getDomainObjectName();
                Optional<FullyQualifiedJavaType> typeOptional = compilationUnit.getSuperInterfaceTypes().stream().findFirst();
                String superInterfaceStr = null;
                FullyQualifiedJavaType superJavaType = null;
                if(typeOptional.isPresent()){
                    superJavaType = typeOptional.get();
                    superInterfaceStr = superJavaType.getShortName() + String.format("<%s>", modelName);
                }
                Interface newInterFace = new Interface(new FullyQualifiedJavaType(introspectedTable.getDAOInterfaceType()));
                newInterFace.setVisibility(JavaVisibility.PUBLIC);
                if(superInterfaceStr != null){
                    FullyQualifiedJavaType superInterface = new FullyQualifiedJavaType(superJavaType.getFullyQualifiedName());
                    newInterFace.addSuperInterface(superInterface);
                    newInterFace.addImportedType(superInterface);
                    newInterFace.addAnnotation("@Repository");
                    newInterFace.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Repository"));
                    compilationUnit = newInterFace;
                }
            }
            GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
                    context.getJavaModelGeneratorConfiguration().getTargetProject(),
                    context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING),
                    context.getJavaFormatter());
            answer.add(gjf);
        }

        return answer;
    }




    /*
     * @chuanyu.liang add
     */
    public List<CompilationUnit> getExtCompilationUnits(IntrospectedTable introspectedTable, String rootClass, String interfaceClass) {
        //ProgressCallback progressCallback = generator.getProgressCallback();
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        //progressCallback.startTask(getString("Progress.8", table.toString())); //$NON-NLS-1$
        CommentGenerator commentGenerator = context.getCommentGenerator();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        TopLevelClass topLevelClass = new TopLevelClass(type);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
//        commentGenerator.addJavaFileComment(topLevelClass);
        if (commentGenerator instanceof DefaultCommentGenerator) {
            topLevelClass.addJavaDocLine("/**");
            topLevelClass.addJavaDocLine(" * Model [" + introspectedTable.getFullyQualifiedTable().getIntrospectedTableName() + "]");
            topLevelClass.addJavaDocLine(" * <p>");
            topLevelClass.addJavaDocLine(" * You can modify it.");
            topLevelClass.addJavaDocLine(" * ");
            topLevelClass.addJavaDocLine(" * @version 0.0.1 ");
            topLevelClass.addJavaDocLine(" */");
        }
        FullyQualifiedJavaType superClass = new FullyQualifiedJavaType(rootClass);
        topLevelClass.setSuperClass(superClass);
        topLevelClass.addImportedType(superClass);

        if (interfaceClass != null && interfaceClass.trim().length() > 0) {
            FullyQualifiedJavaType superInterfaceClass = new FullyQualifiedJavaType(interfaceClass);
            topLevelClass.addSuperInterface(superInterfaceClass);
            topLevelClass.addImportedType(superInterfaceClass);
        }

        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        answer.add(topLevelClass);
        return answer;
    }
}
