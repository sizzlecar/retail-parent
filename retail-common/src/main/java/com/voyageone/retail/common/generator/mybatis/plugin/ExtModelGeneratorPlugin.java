package com.voyageone.retail.common.generator.mybatis.plugin;

import com.voyageone.retail.common.generator.ExtIntrospectedTableMyBatis3SimpleImpl;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractGenerator;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.mybatis3.model.BaseRecordGenerator;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

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

        return !checkFileExist(gjf);
    }

    @SuppressWarnings("Duplicates")
    private boolean checkFileExist(GeneratedJavaFile gxf) {
        DefaultShellCallback shellCallback = new DefaultShellCallback(true);
        File directory;
        try {
            directory = shellCallback.getDirectory(gxf.getTargetProject(), gxf.getTargetPackage());
        } catch (ShellException e) {
            throw new RuntimeException(e);
        }
        File targetFile = new File(directory, gxf.getFileName());
        return targetFile.exists();
    }


    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        return topLevelClass.getType().getFullyQualifiedName().endsWith("_Field");
    }


    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> answer = new ArrayList<>();
        ExtIntrospectedTableMyBatis3SimpleImpl introspectedTableMyBatis3 = (ExtIntrospectedTableMyBatis3SimpleImpl) introspectedTable;
        List<GeneratedJavaFile> generatedJavaFiles = introspectedTable.getGeneratedJavaFiles();
        List<GeneratedJavaFile>
        for (GeneratedJavaFile generatedJavaFile : generatedJavaFiles){

        }
        for (AbstractJavaGenerator javaGenerator : introspectedTableMyBatis3.getJavaModelGenerators()) {
            List<CompilationUnit> compilationUnits;
            if (javaGenerator instanceof BaseRecordGenerator) {
                compilationUnits = getExtCompilationUnits(introspectedTable,introspectedTable.getBaseRecordType() + "_Field", null);
            } else {
                continue;
            }
            for (CompilationUnit compilationUnit : compilationUnits) {
                GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
                        context.getJavaModelGeneratorConfiguration().getTargetProject(),
                        context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING),
                        context.getJavaFormatter());
                if (!checkFileExist(gjf)) {
                    answer.add(gjf);
                }
            }

            introspectedTable.setBaseRecordType(introspectedTable.getBaseRecordType() + "_Field");
            compilationUnits = javaGenerator.getCompilationUnits();
            for (CompilationUnit compilationUnit : compilationUnits) {
                if (compilationUnit instanceof InnerClass) {
                    ((InnerClass)compilationUnit).setAbstract(true);
                    //((InnerClass)compilationUnit).setVisibility(JavaVisibility.DEFAULT);

                    List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
                    if (primaryKeyColumns != null && primaryKeyColumns.size() == 1) {
                        String idClassShortName = primaryKeyColumns.get(0).getFullyQualifiedJavaType().getShortName();
                        String superClass = compilationUnit.getSuperClass().getFullyQualifiedName() + String.format("<%s>", idClassShortName);
                        ((InnerClass) compilationUnit).setSuperClass(superClass);
                    }
                }
                GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
                        context.getJavaModelGeneratorConfiguration().getTargetProject(),
                        context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING),
                        context.getJavaFormatter());
                answer.add(gjf);
            }
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

        if (interfaceClass != null && interfaceClass.trim().length() >0) {
            FullyQualifiedJavaType superInterfaceClass = new FullyQualifiedJavaType(interfaceClass);
            topLevelClass.addSuperInterface(superInterfaceClass);
            topLevelClass.addImportedType(superInterfaceClass);
        }

        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        answer.add(topLevelClass);
        return answer;
    }
}
