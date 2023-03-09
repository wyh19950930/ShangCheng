package com.setsuna.common.commonutils;

import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDFUtil {
    public static final String TAG = "PDFUtil";

    public static boolean createPdfFromView(View content, int width, int height, final String pdfPath) {

        if (content == null || pdfPath == null) {
            return false;
        }
        File sd= Environment.getExternalStorageDirectory();
        String path=sd.getPath()+"/jymj";
        File file =new File(path);
        if(!file.exists()&&!file.isDirectory())
        {
            System.out.println("//不存在");
            file.mkdir();
        } else
        {
            System.out.println("//目录存在");
        }
        // create a new document
        PdfDocument document = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            document = new PdfDocument();
            // crate a page description
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(
                    width, height, 1)
                    .create();

            // start a page
            PdfDocument.Page page = document.startPage(pageInfo);

            // draw something on the page
//        LayoutInflater li = LayoutInflater.from(getApplicationContext());
//        View content = li.inflate(R.layout.activity_welcome, null);
            Canvas canvas = page.getCanvas();
            content.draw(canvas);

            // finish the page
            document.finishPage(page);
            // add more pages
            // write the document content
            FileOutputStream os = null;
            try {
                Log.i(TAG, "String:" + pdfPath);
                os = new FileOutputStream(pdfPath);
                document.writeTo(os);
                os.close();
                return true;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            } finally {
                // close the document
                document.close();
            }
        }else {
            return false;
        }
    }
    /*public static void createPdfFromView(View content, int width, int height, final String pdfPath) {
        if (content == null || pdfPath == null) {
            Log.e(TAG, "content and pdfPath can not be null!");
        }
        // create a new document
        PdfDocument document = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            document = new PdfDocument();
            // crate a page description
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(
                    width, height, 1)
                    .create();

            // start a page
            PdfDocument.Page page = document.startPage(pageInfo);

            // draw something on the page
//        LayoutInflater li = LayoutInflater.from(getApplicationContext());
//        View content = li.inflate(R.layout.activity_welcome, null);
            Canvas canvas = page.getCanvas();
            content.draw(canvas);

            // finish the page
            document.finishPage(page);
            // add more pages
            // write the document content
            FileOutputStream os = null;
            try {
                Log.i(TAG, "String:" + pdfPath);
                os = new FileOutputStream(pdfPath);
                document.writeTo(os);
                os.close();
                ToastUtils.showLong("数据文件保存在" + pdfPath);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                ToastUtils.showLong("导出失败！");
            } finally {
                // close the document
                document.close();
            }
        }
    }*/
}
