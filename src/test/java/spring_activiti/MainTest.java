/*package spring_activiti;

import java.io.InputStream;
import java.util.zip.ZipInputStream;




import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

public class MainTest{
	*//**
     * 部署流程定义使用ClassPath方式（添加）
     * 表的变化：
     * act_re_deployment  流程部署表
     * act_re_procdef  流程定义表
     * act_ge_bytearray  资源文件表
     * act_ge_property   系统配置表
     * *//*

     @Test
     public void TestflowWithClassPath(){
         private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();//获取流程引擎对象（Activiti的核心对象）

          Deployment deployment =  processEngine.getRepositoryService()  //根据调用流程引擎对象的RepositoryService方法获得 deployment 对象
         .createDeployment()
         .addClasspathResource("diragrams/studentLeaveProcess.bpmn")
         .addClasspathResource("diragrams/studentLeaveProcess.png")
         .name("学生请假流程")
         .deploy();
          //查询流程部署表（以下数据信息）
          System.out.println("流程部署id:"+deployment.getId());
          System.out.println("流程部署名称:"+deployment.getName());
      }

 *//**
  * 部署流程定义使用Zip方式（添加）
  *
  * 
  * 
  * *//*

 @Test
 public void TestFlowWithZip(){
     InputStream inputStream = this.getClass()         //获取当前类对象
         .getClassLoader()  //获取类加载器
         .getResourceAsStream("diragrams/HelloWord.zip");  //获取指定文件资源
     ZipInputStream zipInputStream = new ZipInputStream(inputStream);
     Deployment deployment =  processEngine.getRepositoryService()
     .createDeployment().addZipInputStream(zipInputStream)
     .name("lmy1")
     .deploy();
     System.out.println("流程部署id:"+deployment.getId());
     System.out.println("流程部署名称:"+deployment.getName());

 }

}
*/