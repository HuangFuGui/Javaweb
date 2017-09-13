package complexService.download;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface downloadService {

    void downloadSolve(int id, HttpServletRequest request, HttpServletResponse response, int userId)
            throws ServletException, IOException;
}

