package cn.liondance.liondanceapi.common;

import cn.liondance.liondanceapi.enums.OperateType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author sunwei
 * 自定义EntityListener 工作记录操作日志的实例
 * 通过自定义的ActionsLogsAuditListener来监听我们要处理
 * 日志的实体，然后将事件变更，进行异步处理，这样就
 * 可以完全解耦了。当然了，这里我们解耦的方式也可以通过Spring的
 * 事件机制进行解决。
 * 真实发生的数据和状态及其时间即可，具体变化了什么那是在业务展
 * 示层面上要做的事情，这里没有必要做比对的事情，记住这一点之后
 * 就会让你的日志处理实现机制豁然明朗，变得容易许多。
 */

@Slf4j
@WebListener
public class ActionsLogsAuditListener implements ServletContextListener {

    // 在此初始化WebApp,例如打开数据库连接池等:
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("WebApp initialized.");
    }

    // 在此清理WebApp,例如关闭数据库连接池等:
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("WebApp destroyed.");
    }

    @PrePersist
    private void prePersist(Object object) {
        //保存后要做的事
        notice(object, OperateType.create);
    }

    @PostLoad
    private void postLoad(Object object) {
        //更新的时候要做的事
        notice(object, OperateType.load);
    }

    @PreRemove
    private void preRemove(Object object) {
        //删除后要做的事
        notice(object, OperateType.remove);
    }

    @PreUpdate
    private void preUpdate(Object object) {
        //更新后要做的事
        notice(object, OperateType.update);
    }


    @Async
    public void notice(Object object, OperateType operateType) {
        log.warn("object {} operateType {}", object, operateType);
        // actionsLogRepository.save(ActionsLog.builder().content(JSON.toJSONString(object)).operateType(operateType).build());
    }


}
