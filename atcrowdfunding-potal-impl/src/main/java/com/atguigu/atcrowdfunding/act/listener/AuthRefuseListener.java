package com.atguigu.atcrowdfunding.act.listener;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.service.MemberService;
import com.atguigu.atcrowdfunding.service.TicketService;
import com.atguigu.atcrowdfunding.utils.ApplicationContextUtils;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.context.ApplicationContext;

/**
 * 流程拦截器(拒绝)
 * <p/>
 * Created by SunBo on 2017-07-27.
 */
public class AuthRefuseListener implements ExecutionListener {

    //监听器对象是自己创建的,不是IOC容器创建的,所以不能直接进行自动装配.
    //@Autowired
    //private MemberService memberService;

    /**
     * @param execution
     * @throws Exception
     */
    @Override
    public void notify(DelegateExecution execution) throws Exception {
        //ApplicationContext ioc = new ClassPathXmlApplicationContext("spring/spring-*.xml");

        //WebApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(application);


        ApplicationContext ioc = ApplicationContextUtils.applicationContext;
        MemberService memberService = ioc.getBean(MemberService.class);

        //修改t_member表authstatus字段值:会员申请状态:2-已实名认证
        Integer memberid = (Integer) execution.getVariable("memberid");
        Member member = new Member();
        member.setId(memberid);
        member.setAuthstatus("0");
        memberService.updateAuthstatus(member);

        //修改t_ticket表status字段值:1-审核结束
        TicketService ticketService = ioc.getBean(TicketService.class);
        ticketService.updateTicketStatus(memberid);
    }
}
