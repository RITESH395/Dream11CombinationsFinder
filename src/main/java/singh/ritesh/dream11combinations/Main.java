package singh.ritesh.dream11combinations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import singh.ritesh.dream11combinations.helper.FileHelper;
import singh.ritesh.dream11combinations.service.implementation.CombinationFinderServiceImpl;

@SpringBootApplication
public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Only for cricket");
        ApplicationContext ctx = SpringApplication.run(Main.class, args);

        /*
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames)
        {
            System.out.println(beanName);
        }

        */

        CombinationFinderServiceImpl combinationFinderService = new CombinationFinderServiceImpl();
        System.out.println(combinationFinderService.getAllCombinations(FileHelper.getHardcodedPlayers()));
    }

}