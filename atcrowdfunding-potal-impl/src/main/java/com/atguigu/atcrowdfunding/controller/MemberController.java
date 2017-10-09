package com.atguigu.atcrowdfunding.controller;

import java.io.File;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import com.atguigu.atcrowdfunding.act.listener.AuthPassListener;
import com.atguigu.atcrowdfunding.act.listener.AuthRefuseListener;
import com.atguigu.atcrowdfunding.bean.MemberCert;
import com.atguigu.atcrowdfunding.utils.Datas;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Ticket;
import com.atguigu.atcrowdfunding.service.CertService;
import com.atguigu.atcrowdfunding.service.MemberService;
import com.atguigu.atcrowdfunding.service.TicketService;
import com.atguigu.atcrowdfunding.utils.AjaxResult;
import com.atguigu.atcrowdfunding.utils.Const;
import org.springframework.web.multipart.MultipartFile;


/**
 * The type Member controller.
 */
@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private CertService certService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    /**
     * 跳转到申请审核页面
     *
     * @return string string
     */
    @RequestMapping("/acctType")
    public String acctType() {
        return "member/accttype";
    }

    /**
     * 会员基本信息
     *
     * @return string string
     */
    @RequestMapping("/basicInfo")
    public String basicinfo() {
        return "member/basicinfo";
    }

    /**
     * 认证审核资质上传
     *
     * @return string string
     */
    @RequestMapping("/uploadFile")
    public String uploadfile() {
        return "member/uploadfile";
    }


    /**
     * 跳转到邮箱验证码填写页面
     *
     * @return the string
     */
    @RequestMapping("/checkApply")
    public String checkApply() {
        return "member/checkapply";
    }

    /**
     * Apply string.
     *
     * @param session the session
     * @param map     the map
     * @return the string
     */
    @RequestMapping("/apply")
    public String apply(HttpSession session, Map<String, Object> map) {

        Member member = (Member) session.getAttribute(Const.LOGIN_MEMBER);


        Ticket ticket = ticketService.queryTicketByMemberId(member.getId());
        //	获取流程单,判断流程单是否存在:
        //	如果不存在,保存流程单,跳转账户类型页面
        //  如果存在,根据流程单中保存的已有步骤,跳转相关页面(记忆功能)


        if (ticket == null) {

            ticket = new Ticket();
            ticket.setMemberid(member.getId());
            ticket.setStatus("0");

            ticketService.saveTicket(ticket);
            // 跳转到会员账户选择页面
            return "member/accttype";
        } else {

            String pstep = ticket.getPstep();

            if ("accttype".equals(pstep)) {
                // 跳转到会员基本信息填写页面
                return "member/basicinfo";
            } else if ("basicinfo".equals(pstep)) {
                String accttype = member.getAccttype();
                List<Cert> certList = certService.queryCertByAcctType(accttype);

                map.put("certList", certList);
                // 跳转到会员资质上传页面
                return "member/uploadfile";

            } else if ("uploadfile".equals(pstep)) {
                // 跳转到邮箱地址获取验证码页面
                return "member/checkemail";

            } else if ("checkemail".equals(pstep)){
                // 跳转输入验证码页面
                return "member/checkapply";
            }
        }
        return "member/accttype";
    }

    /**
     * 更新会员基本信息
     *
     * @param member  the member
     * @param session the session
     * @return object object
     */
    @ResponseBody
    @RequestMapping("/updateBasicInfo")
    public Object updateBasicinfo(Member member, HttpSession session) {

        AjaxResult result = new AjaxResult();

        try {
            Member loginMember = (Member) session.getAttribute(Const.LOGIN_MEMBER);

            loginMember.setRealname(member.getRealname());
            loginMember.setCardnum(member.getCardnum());
            loginMember.setTel(member.getTel());

            int count = memberService.updateBasicInfo(loginMember);

            Ticket ticket = ticketService.queryTicketByMemberId(loginMember.getId());
            ticket.setPstep("basicinfo");

            int count2 = ticketService.updateTicketProcessStep(ticket);

            result.setSuccess((count == 1) && (count2 == 1));
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
    }

    /**
     * 选择账户类型
     *
     * @param accttype the accttype
     * @param session  the session
     * @return object object
     */
    @ResponseBody
    @RequestMapping("/updateAcctType")
    public Object updateAcctType(String accttype, HttpSession session) {

        AjaxResult result = new AjaxResult();

        try {
            Member member = (Member) session.getAttribute(Const.LOGIN_MEMBER);

            member.setAccttype(accttype);

            int count = memberService.updateAcctType(member);

            Ticket ticket = ticketService.queryTicketByMemberId(member.getId());
            ticket.setPstep("accttype");

            int count2 = ticketService.updateTicketProcessStep(ticket);

            result.setSuccess((count == 1) && (count2 == 1));
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
    }

    /**
     * 上传多文件(资质审核需要多张资质照片)
     *
     * @param session the session
     * @param ds      the ds
     * @return object object
     */
    @ResponseBody
    @RequestMapping("/uploadCertFile")
    public Object uploadCertFile(HttpSession session, Datas ds) {
        // 给Ajax返回标识信息(Ajax可以根据返回的结果来进行不同的操作)
        AjaxResult result = new AjaxResult();

        try {
            // 获取到Application域对象(通过Application来获取项目的上下文路径)
            ServletContext application = session.getServletContext();
            // 获取到项目的上下文路径
            // 获取到项目下的pic目录的真实路径, 用于存储会员上传的资质图片
            String picPath = application.getRealPath("pic");
            // 保存操作会将会员与资质进行关联(memberid/certid/iconpath)
            // 获取会员的ID(将从session域中获取该会员的信息)
            Member member = (Member) session.getAttribute(Const.LOGIN_MEMBER);

            // 将获取到的资质进行迭代
            for (MemberCert memberCert : ds.getCertimgs()) {

                // 将会员ID存放到MemberCert对象当中
                memberCert.setMemberid(member.getId());

                // 获取到文件本身的对象
                MultipartFile file = memberCert.getMultiPartFile();
                // 获取文件的真实名称
                String realFilename = file.getOriginalFilename();

                // 获取到文件的后缀名称(扩扩展名称)
                // 获取到扩展名称以"."为开始字符进行截取, 包含"."一直截取到最后
                String extendName = realFilename.substring(realFilename.indexOf("."));

                // 生成一个UUID来与扩展名称进行拼接
                // 将文件的真实名称替换成uuid(xxxxx-xxxxx-xxxx-xxxx-xxxxx-xxxx)
                String uuid = UUID.randomUUID().toString();
                // 将UUID中的"-"去除
                String replaceFileName = uuid.replaceAll("\\-", "");

                // 将文件名称与扩展名称进行拼接
                String finalFileName = replaceFileName + extendName;

                // 将新的文件名称赋值到对象的iconpath的属性
                memberCert.setIconpath(finalFileName);

                // 文件的路径
                String filePath = picPath + "\\cert\\" + finalFileName;

                file.transferTo(new File(filePath));
            }

            memberService.saveMemberCertInfo(ds.getCertimgs());

            // 更新流程单中的操作步骤
            // 首先获取到该会员的ID
            Ticket ticket = ticketService.queryTicketByMemberId(member.getId());

            // 根据会员的ID来对流程单进行更新
            ticket.setPstep("uploadfile");

            ticketService.updateTicketProcessStep(ticket);

            // 设置操作成功的标识信息
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            // 设置错误状态
            result.setSuccess(false);
            // 设置状态返回的信息
            result.setErrorMessage("资质文件上传失败!");
        }
        return result;
    }

    /**
     * Check email object.
     *
     * @param session the session
     * @param email   the email
     * @return the object
     */
    @ResponseBody
    @RequestMapping("/checkEmail")
    public Object checkEmail(HttpSession session, String email) {
        AjaxResult result = new AjaxResult();

        try{
            // 有可能会员在申请认证的时候会更改邮箱的账号, 这时需要将邮件发送到这个更改后的邮箱地址
            // 在发邮件的时候会从会员表(t_member)表中从新获取该字段的值
            // 为了对数据库进行没必要的操作, 首先应该判断修改的邮箱地址与原有的邮箱地址是否一致
            // 如果是一致的, 那么是没有必要去操作数据库进行修改的
            // 首先从Session域中获取到已登录用户的账号信息
            Member member = (Member) session.getAttribute(Const.LOGIN_MEMBER);

            // 准备数据(验证码), 随机生成四位数的验证码
            // StringBuilder是线程不安全的, 但是效率高
            StringBuilder authcode = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                // 返回一个伪随机数，它是取自此随机数生成器序列的、
                // 在 0（包括）和指定值（不包括）之间均匀分布的 int 值。
                // 生成0~9随机的十位数
                authcode.append(new Random().nextInt(10));
            }


            // 判断session域中的Email值与会员传过来的值是否一致
            if (!member.getEmail().equals(email)) {

                // 如果不一致先将修改的值设置到session域中
                member.setEmail(email);
                // 更新session域
                session.setAttribute(Const.LOGIN_MEMBER, member);
                // 然后在更新数据库
                memberService.updateMemberEmailByMember(member);
            }

            // 将数据库中的字段值更新之后, 启动流程
            // 如果一致那么要启动流程框架(简单的流程总结)
            // 流程框架当中用到的流程变量:
            // 1.要用到当前登录账号的会员的邮箱地址${toMail}, 要发送验证码${authcode}
            // 2.如果验证码验证成功了, 那么需要会员自己进行验证${loginacct}
            // 3.此时验证会员用户的流程已经到了后台, 那么后台审核是以组为单位进行审核的${backuser}
            // 4.如果审核通过了, 需要传入流程变量值${flag == true}
            // 5.如果审核失败了, 需要传入流程变量值${flag == false}

            //------------------------------------------------------//
            // 启动流程框架(此时与测试阶段不同
            // 流程框架中的服务已经交由Spring进行管理
            // 直接从IOC容器当中进行获取)
            // 直接通过流程定义的KEY获取流程定义(由于流程定义在此处是固定的[auth], 那么以后后台
            // 上传的流程定义必须命名为该名称, 否则是获取不到流程定义的)
            ProcessDefinition processDefinition = repositoryService
                    .createProcessDefinitionQuery()
                    .processDefinitionKey("auth").latestVersion()
                    .singleResult();
            // 启动流程实例(根据上面获取到的流程定义来启动流程的实例)
            // 启动流程实例时会用到流程变量
            // 准备流程变量值
            Map<String, Object> variables = new HashMap<String, Object>();
            // 设置流程变量(邮箱地址)
            variables.put("toMail", email);
            // 设置验证码
            variables.put("authcode", authcode);
            // 设置验证验证码的账号名称
            variables.put("loginacct", member.getLoginacct());
            // 设置后台验证通过的拦截器
            variables.put("passListener", new AuthPassListener());
            // 设置后台验证拒绝的拦截器
            variables.put("refuseListener", new AuthRefuseListener());
            // 根据流程定义ID启动流程实例, 然后获取启动流程实例ID
            ProcessInstance processInstance =
                    runtimeService.startProcessInstanceById(processDefinition.getId(), variables);

            // 更新审批流程进度
            Ticket ticket = ticketService.queryTicketByMemberId(member.getId());
            // 更新进度标识(pstep)
            ticket.setPstep("checkemail");
            // 把验证码也要更新到数据库当中, 准备下一步进行审核
            ticket.setAuthcode(authcode.toString());
            // 更新流程单的实例ID
            ticket.setPiid(processInstance.getId());
            // 更新流程单的字段
            int count = ticketService.updateTicketInfo(ticket);
            // 设置AJAX回显标志
            result.setSuccess(count == 1);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setErrorMessage("邮箱验证失败!");
        }
        return result;
    }


    /**
     * 验证码验证完成之后, 跳转到会员的首页面
     *
     * Finish apply object.
     *
     * @param session  the session
     * @param authcode the authcode
     * @return the object
     */
    @ResponseBody
    @RequestMapping("/finishApply")
    public Object finishApply(HttpSession session, String authcode) {
        AjaxResult result = new AjaxResult();
        try {

            // 获取到当前登录的会员账号信息
            Member member = (Member) session.getAttribute(Const.LOGIN_MEMBER);

            // 从流程单(t_ticket)中查询验证码(authcode)
            Ticket ticket = ticketService.queryTicketByMemberId(member.getId());

            // 判断输入的验证码是否正确
            if (ticket.getAuthcode().equals(authcode)) {
                // 完成[审核验证码]任务
                TaskQuery taskQuery = taskService.createTaskQuery();
                //
                Task task = taskQuery.processInstanceId(ticket.getPiid())
                        .taskAssignee(member.getLoginacct()).singleResult();
                taskService.complete(task.getId());

                member.setAuthstatus("1");
                memberService.updateAuthstatus(member);
                result.setSuccess(true);
            } else {
                //给予提示消息
                result.setErrorMessage("您输入的验证码不正确,请重新输入!");
                result.setSuccess(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }
}
