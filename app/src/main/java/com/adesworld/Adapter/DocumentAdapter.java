package com.adesworld.Adapter;

import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adesworld.Model.DocumentData;
import com.adesworld.R;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Created by ssasa on 07-Jul-18.
 */

public class DocumentAdapter extends RecyclerView.Adapter <DocumentAdapter.DocumentViewHolder>{
    private ArrayList <DocumentData> bookList;
    private LinearLayout recycler_item ;
    private LayoutInflater layoutInflater ;

    public DocumentAdapter (ArrayList<DocumentData> bookList)
    {
        this.bookList=bookList;
    }
    @Override
    public DocumentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.recycler_document_item,parent,false);
        return new DocumentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DocumentViewHolder holder, final int position) {
        holder.tv_BookName.setText(bookList.get(position).getName());
        recycler_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //holder.tv_BookName.setText("Downloading");
                writeResponseBodyToDisk(bookList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class DocumentViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_BookName ;
        public DocumentViewHolder(View itemView) {
            super(itemView);
            tv_BookName = (TextView) itemView.findViewById(R.id.tv_BookName);
            recycler_item = (LinearLayout) itemView.findViewById(R.id.recycler_item);
        }
    }

    private boolean writeResponseBodyToDisk(DocumentData book) {
        try {
            String dir = book.getDir();
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+File.separator +book.getName());
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = dir.length();
                long fileSizeDownloaded = 0;

                inputStream = new ByteArrayInputStream(dir.getBytes(StandardCharsets.UTF_8));
                outputStream = new FileOutputStream(file);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}
