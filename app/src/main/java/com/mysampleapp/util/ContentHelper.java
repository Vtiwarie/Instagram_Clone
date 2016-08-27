//
// Copyright 2016 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.8
//
package com.mysampleapp.util;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import java.io.File;

import com.mysampleapp.R;

/**
 * This class provides a convenient method to open a file in a file viewer that
 * is appropriate for the file's content type.
 */
public class ContentHelper {

    /**
     * Open file in external viewer.
     * @param context context
     * @param file file
     */
    public static void openFileViewer(final Context context,
                                      final File file) {

        // Make the content available using our file provider.
        final Intent shareIntent = new Intent();

        final Uri contentUri = FileProvider.getUriForFile(
                context,
                context.getString(R.string.content_file_provider_authority),
                file);

        shareIntent.setDataAndType(contentUri,
                context.getContentResolver().getType(contentUri));
        shareIntent.addFlags(
                Intent.FLAG_GRANT_READ_URI_PERMISSION);
        shareIntent.setAction(Intent.ACTION_VIEW);

        try {
            context.startActivity(shareIntent);
        } catch (ActivityNotFoundException ex) {
            final AlertDialog.Builder errorDialogBuilder = new AlertDialog.Builder(context);
            errorDialogBuilder.setTitle(context.getString(R.string.content_helper_error_title));
            errorDialogBuilder.setMessage(
                    String.format(context.getString(R.string.content_helper_error_no_app),
                            shareIntent.getType()));
            errorDialogBuilder.setNeutralButton(
                    context.getString(R.string.content_dialog_ok), null);
            errorDialogBuilder.show();
        }
    }
}
