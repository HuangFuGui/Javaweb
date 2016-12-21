package complexService.plupload;

import entity.Plupload;

import java.io.File;
import java.io.InputStream;

/**
 * Created by huangfugui on 2016/12/20.
 */
public interface pluploadService {

    void upload(Plupload plupload,File pluploadDir);

    void upload(Plupload plupload, File pluploadDir, String fileName);

    void savePluploadFile(InputStream inputStream, File tempFile, boolean flag);
}
