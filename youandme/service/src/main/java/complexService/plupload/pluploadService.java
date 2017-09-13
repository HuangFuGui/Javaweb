package complexService.plupload;

import entity.Plupload;

import java.io.File;
import java.io.InputStream;

public interface pluploadService {

    void upload(Plupload plupload,File pluploadDir);

    void upload(Plupload plupload, File pluploadDir, String fileName);

    void savePluploadFile(InputStream inputStream, File tempFile, boolean flag);

}
