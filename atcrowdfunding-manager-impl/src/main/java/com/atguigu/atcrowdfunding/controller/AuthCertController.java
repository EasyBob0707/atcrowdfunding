package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.MemberCert;
import com.atguigu.atcrowdfunding.bean.Ticket;
import com.atguigu.atcrowdfunding.service.AuthCertService;
import com.atguigu.atcrowdfunding.service.MemberService;
import com.atguigu.atcrowdfunding.service.TicketService;
import com.atguigu.atcrowdfunding.utils.AjaxResult;
import com.atguigu.atcrowdfunding.utils.Page;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SunBo on 2017-07-27.
 */
@RequestMapping("/authCert")
@Controller
public class AuthCertController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private AuthCertService authCertService;

    /**
     * 跳转到申请认证列表页面
     *
     * @return string string
     */
    @RequestMapping("/index")
    public String index() {
        return "authcert/index";
    }

    /**
     * 进入Index页面时, 默认发起一个Ajax的异步请求
     *
     * @param pageno   the pageno
     * @param pagesize the pagesize
     * @return object object
     */
    @ResponseBody
    @RequestMapping("/queryPage")
    public Object queryPage(
            @RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
            @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pagesize) {
        AjaxResult result = new AjaxResult();

        try {
            // 查询当前任务列表(通过任务的服务获取到任务的查询对象)
            TaskQuery taskQuery = taskService.createTaskQuery();
            // 流程图是通过backuser来获取任务的, 那么就查询backuser这个组下的任务(任务列表)
            List<Task> taskList = taskQuery.taskCandidateGroup("backuser").listPage((pageno - 1) * pagesize, pagesize);

            // 将数据存放到List集合当中进行迭代, List当中存放map集合
            List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
            // 循环遍历任务清单
            for (Task task : taskList) {
                Map<String, Object> paraMap = new HashMap<String, Object>();

                // 获取任务相关的字段值
                // 获取任务的名称
                paraMap.put("taskName", task.getName());
                // 获取任务的ID
                paraMap.put("taskid", task.getId());

                // 获取定义的查询对象
                ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

                //  通过查询对象获取最新版本的对象
                ProcessDefinition auth = processDefinitionQuery.processDefinitionKey("auth").latestVersion().singleResult();

                // 获取流程定义的名称
                paraMap.put("processDefName", auth.getName());
                // 获取流程定义的版本号
                paraMap.put("processDefVersion", auth.getVersion());

                // 衔接思路: 通过流程任务的ID来获取会员
                // 获取流程实例的ID
                String processInstanceId = task.getProcessInstanceId();

                System.out.println("task.getProcessInstanceId():"+processInstanceId);

                System.out.println("taskid: "+task.getId());

                Ticket ticket = ticketService.queryTicketByPiid(processInstanceId);

                Member member = memberService.queryMemberByMemberId(ticket.getMemberid());

                // 获取会员的名称
                paraMap.put("membername", member.getUsername());
                paraMap.put("memberid", ticket.getMemberid());

                // 最后都存放到list当中
                data.add(paraMap);
            }

            // 获取到任务的数量
            Long taskNum = taskQuery.taskCandidateGroup("backuser").count();

            Page<Map<String,Object>> page = new Page<Map<String,Object>>(pageno,pagesize);
            page.setData(data);
            page.setTotalsize(taskNum.intValue());

            result.setSuccess(true);
            result.setPage(page);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 回显会员上传的资质信息用于审核使用
     *
     * @param memberid the memberid
     * @param map      the map
     * @return string string
     */
    @RequestMapping("/show")
    public String show(Integer memberid, Map<String, Object> map) {
        // 获取会员的信息
        Member member = memberService.queryMemberByMemberId(memberid);
        map.put("member", member);

        // 根据会员的ID获取会员上传的资质信息
        List<Map<String,Object>> dataList = authCertService.queryAuthCertInfo(memberid);

        map.put("dataList", dataList);

        return "authcert/show";
    }


    /**
     * 通过认证审核
     * Pass auth cert object.
     *
     * @param taskid   the taskid
     * @param memberid the memberid
     * @return the object
     */
    @ResponseBody
    @RequestMapping("/passAuthCert")
    public Object passAuthCert(String taskid,Integer memberid) {
        AjaxResult result = new AjaxResult();

        try {

            taskService.setVariable(taskid, "flag", true);
            taskService.setVariable(taskid, "memberid", memberid);
            taskService.complete(taskid);

            result.setSuccess(true);
        } catch ( Exception e ) {
            e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
    }


    /**
     * 拒绝认证审核
     * Refuse auth cert object.
     *
     * @param taskid   the taskid
     * @param memberid the memberid
     * @return the object
     */
    @ResponseBody
    @RequestMapping("/refuseAuthCert")
    public Object refuseAuthCert(String taskid,Integer memberid) {
        AjaxResult result = new AjaxResult();

        try {

            taskService.setVariable(taskid, "flag", false);
            taskService.setVariable(taskid, "memberid", memberid);
            taskService.complete(taskid);

            result.setSuccess(true);
        } catch ( Exception e ) {
            e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
    }
}
