/*
 * Copyright (c) 2018 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.files.provider.common;

import java.util.Objects;
import java.util.Set;

import java8.nio.file.LinkOption;
import java8.nio.file.OpenOption;
import java8.nio.file.StandardOpenOption;

public class OpenOptions {

    private final boolean mRead;
    private final boolean mWrite;
    private final boolean mAppend;
    private final boolean mTruncateExisting;
    private final boolean mCreate;
    private final boolean mCreateNew;
    private final boolean mDeleteOnClose;
    private final boolean mSync;
    private final boolean mDsync;
    private final boolean mNoFollowLinks;

    private OpenOptions(boolean read, boolean write, boolean append, boolean truncateExisting,
                        boolean create, boolean createNew, boolean deleteOnClose, boolean sync,
                        boolean dsync, boolean noFollowLinks) {
        mRead = read;
        mWrite = write;
        mAppend = append;
        mTruncateExisting = truncateExisting;
        mCreate = create;
        mCreateNew = createNew;
        mDeleteOnClose = deleteOnClose;
        mSync = sync;
        mDsync = dsync;
        mNoFollowLinks = noFollowLinks;
    }

    public static OpenOptions fromSet(Set<? extends OpenOption> options) {
        boolean read= false;
        boolean write= false;
        boolean append= false;
        boolean truncateExisting= false;
        boolean create= false;
        boolean createNew= false;
        boolean deleteOnClose= false;
        boolean sync= false;
        boolean dsync= false;
        boolean noFollowLinks= false;
        for (OpenOption option : options) {
            Objects.requireNonNull(option);
            if (!(option instanceof OpenOption)) {
                throw new UnsupportedOperationException(option.toString());
            }
            if (option instanceof StandardOpenOption) {
                StandardOpenOption standardOpenOption = (StandardOpenOption) option;
                switch (standardOpenOption) {
                    case READ:
                        read = true;
                        break;
                    case WRITE:
                        write = true;
                        break;
                    case APPEND:
                        append = true;
                        break;
                    case TRUNCATE_EXISTING:
                        truncateExisting = true;
                        break;
                    case CREATE:
                        create = true;
                        break;
                    case CREATE_NEW:
                        createNew = true;
                        break;
                    case DELETE_ON_CLOSE:
                        deleteOnClose = true;
                        break;
                    // Unsupported
                    //case SPARSE:
                    //    break;
                    case SYNC:
                        sync = true;
                        break;
                    case DSYNC:
                        dsync = true;
                        break;
                    default:
                        throw new UnsupportedOperationException(standardOpenOption.name());
                }
            } else if (option == LinkOption.NOFOLLOW_LINKS) {
                noFollowLinks = true;
            } else {
                throw new UnsupportedOperationException(option.toString());
            }
        }
        return new OpenOptions(read, write, append, truncateExisting, create, createNew,
                deleteOnClose, sync, dsync, noFollowLinks);
    }

    public boolean hasRead() {
        return mRead;
    }

    public boolean hasWrite() {
        return mWrite;
    }

    public boolean hasAppend() {
        return mAppend;
    }

    public boolean hasTruncateExisting() {
        return mTruncateExisting;
    }

    public boolean hasCreate() {
        return mCreate;
    }

    public boolean hasCreateNew() {
        return mCreateNew;
    }

    public boolean hasDeleteOnClose() {
        return mDeleteOnClose;
    }

    public boolean hasSync() {
        return mSync;
    }

    public boolean hasDsync() {
        return mDsync;
    }

    public boolean hasNoFollowLinks() {
        return mNoFollowLinks;
    }
}