package com.voyageone.retail.biz.process.model;

import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class FileByteArrayResource extends AbstractResource {


    private final byte[] byteArray;

    private final String description;

    private final String fileName;


    /**
     * Create a new {@code ByteArrayResource}.
     * @param byteArray the byte array to wrap
     */
    public FileByteArrayResource(byte[] byteArray) {
        this(byteArray, "resource loaded from byte array", "defaultName");
    }

    /**
     * Create a new {@code ByteArrayResource} with a description.
     * @param byteArray the byte array to wrap
     * @param description where the byte array comes from
     */
    public FileByteArrayResource(byte[] byteArray, @Nullable String description) {
        this(byteArray, "resource loaded from byte array", "defaultName");
    }

    public FileByteArrayResource(byte[] byteArray, @Nullable String description, @Nullable String fileName) {
        Assert.notNull(byteArray, "Byte array must not be null");
        this.byteArray = byteArray;
        this.description = (description != null ? description : "");
        this.fileName = (fileName != null ? fileName : "");
    }


    /**
     * Return the underlying byte array.
     */
    public final byte[] getByteArray() {
        return this.byteArray;
    }

    /**
     * This implementation always returns {@code true}.
     */
    @Override
    public boolean exists() {
        return true;
    }

    /**
     * This implementation returns the length of the underlying byte array.
     */
    @Override
    public long contentLength() {
        return this.byteArray.length;
    }

    /**
     * This implementation returns a ByteArrayInputStream for the
     * underlying byte array.
     * @see java.io.ByteArrayInputStream
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(this.byteArray);
    }

    /**
     * This implementation returns a description that includes the passed-in
     * {@code description}, if any.
     */
    @Override
    public String getDescription() {
        return "Byte array resource [" + this.description + "]";
    }


    /**
     * This implementation compares the underlying byte array.
     * @see java.util.Arrays#equals(byte[], byte[])
     */
    @Override
    public boolean equals(@Nullable Object other) {
        return (this == other || (other instanceof ByteArrayResource &&
                Arrays.equals(((FileByteArrayResource) other).byteArray, this.byteArray)));
    }

    /**
     * This implementation returns the hash code based on the
     * underlying byte array.
     */
    @Override
    public int hashCode() {
        return (byte[].class.hashCode() * 29 * this.byteArray.length);
    }

    @Override
    public String getFilename() {
        return this.fileName;
    }
}
