package cn.liondance.liondanceapi;

import org.ansj.splitWord.analysis.NlpAnalysis;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LiondanceApiApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(NlpAnalysis.parse("拜访医生，了解患者类型，探寻医生浅部真菌病常见致病菌及治疗方案，对皮肤癣菌是引起浅部真菌感染的主要致病原菌达成共识； 拜访后，客户反馈念珠菌所占比例逐渐上升，与客户达成的共识：咪康唑对皮肤癣菌的体外易感性良好，下次拜访跟进计划：咪康唑对念珠菌有良好的体外活性。"));
    }

}
