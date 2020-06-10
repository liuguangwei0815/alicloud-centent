package ribbonconfiguration;


import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

/**
 * ribbon的负载规则配置类
 *
 * @author liuwei
 * @date 2020/05/31
 */
@Configurable
public class RibbonRuleConfuguration {
    /**
     * 返回规则（这个名字暂时你写固定吧）
     *
     * @return {@link IRule}
     */
    @Bean
    public IRule ribbonRule(){
        return new RandomRule();
    }
}
