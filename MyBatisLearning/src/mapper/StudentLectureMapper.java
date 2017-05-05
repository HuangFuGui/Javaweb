package mapper;

import po.StudentLectureBean;

import java.util.List;

/**
 * Created by huangfugui on 2017/4/23.
 */
public interface StudentLectureMapper {
    List<StudentLectureBean> findStudentLectureByStuId(int id);
}
