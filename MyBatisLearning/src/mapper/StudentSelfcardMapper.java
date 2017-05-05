package mapper;

import po.StudentSelfcardBean;

/**
 * Created by huangfugui on 2017/4/20.
 */
public interface StudentSelfcardMapper {
    StudentSelfcardBean findStudentSelfcardByStudentId(Long id);
}
