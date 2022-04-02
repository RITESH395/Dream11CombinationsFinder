package singh.ritesh.dream11combinations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Only for cricket");
        ApplicationContext ctx = SpringApplication.run(Main.class, args);

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames)
        {
            System.out.print(beanName + " | ");
        }
        System.out.println();
        System.out.println("====================");
        System.out.println("[SUCCESS] Application start ..");
        System.out.println("====================");
    }
}