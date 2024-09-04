package com.luofan.usercenter.service;

import com.luofan.usercenter.model.domain.Team;
import com.baomidou.mybatisplus.extension.service.IService;
import com.luofan.usercenter.model.domain.User;
import com.luofan.usercenter.model.dto.TeamQuery;
import com.luofan.usercenter.model.request.TeamJoinRequest;
import com.luofan.usercenter.model.request.TeamQuitRequest;
import com.luofan.usercenter.model.request.TeamUpdateRequest;
import com.luofan.usercenter.model.vo.TeamUserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author luofan
* @description 针对表【team】的数据库操作Service
* @createDate 2024-08-20 07:24:40
*/
public interface TeamService extends IService<Team> {

    /**
     * 创建队伍
     * @param team
     * @return
     */
    long addTeam(Team team, User loginUser);

    /**
     * 搜索队伍
     * @param teamQuery
     * @param  isAdmin
     * @return
     */
    List<TeamUserVO> listTeams(TeamQuery teamQuery, boolean isAdmin);

    /**
     * 更新队伍
     * @param teamUpdateRequest
     * @param loginUser
     * @return
     */
    boolean updateTeam(TeamUpdateRequest teamUpdateRequest,User loginUser);

    /**
     * 加入队伍
     * @param teamJoinRequest
     * @param loginUser
     * @return
     */
    boolean joinTeam(TeamJoinRequest teamJoinRequest,User loginUser);

    /**
     * 退出队伍
     * @param teamQuitRequest
     * @param loginUser
     * @return
     */
    boolean quitTeam(TeamQuitRequest teamQuitRequest, User loginUser);

    /**
     * 删除解散队伍
     * @param id
     * @return
     */
    boolean deleteTeam(long id,User loginUser);
}
