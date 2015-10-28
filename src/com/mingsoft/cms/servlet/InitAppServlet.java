package com.mingsoft.cms.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingsoft.basic.biz.IAppBiz;
import com.mingsoft.basic.entity.AppEntity;
import com.mingsoft.basic.servlet.BaseServlet;

/**
 * 
 * 识别用户输入的访问地址,对APP进行初始化</br>
 * 避免用户因为项目发布路径的问题直接对数据库进行修改</br>
 * @author 成卫雄(qq:330216230)
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2015年9月16日 上午9:49:45<br/>
 * 历史修订：<br/>
 */
@WebServlet("/initApp")
public class InitAppServlet extends BaseServlet{

	private int appId = 1549;
	
	 
	/**
	 * 获取当前用户输入的项目访问地址更新应约域名</br>
	 * 更新完成之后将该类部署后的字节码文件修改成备份文件</br>
	 * 初始化完成之后用户不能再访问改类文件</br>
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户项目的访问路径(地址+端口+发布后的项目名)
		String url = this.getUrl(request);
		logger.debug("InitAppServlet-----------"+url+"====");
		//根据AppId,将数据库中的域名更新成用户最新的域名
		IAppBiz appBiz = (IAppBiz) this.getBean(request.getServletContext(),"appBiz");
		AppEntity appEntity = new AppEntity();
		appEntity.setAppId(appId);
		appEntity.setAppUrl(url);
		
		appBiz.updateEntity(appEntity);//执行更新
		//获取tomcat下面默认的manager文件夹
		File file = new File(request.getSession().getServletContext().getRealPath("/"));
		file = new File(file.getParent()+File.separator+"/manager");
		if(file.exists()){
			file.delete();
		}
		response.sendRedirect(url+File.separator+"ms"+File.separator+"login.do");
//		//更新完成之后将该类发布后的字节码文件修改成备份文件
//		File pathFile = new File(this.getClass().getResource("").getPath()); 
//		String initAppPath = pathFile.toString()+File.separator+"InitAppServlet.class";
//		//原文件名
//		File toBeRenamed = new File(initAppPath);
//		//修改后的文件
//		File newFile = new File(pathFile+File.separator+"InitAppServlet.class.bak");
//		Boolean fileResult = toBeRenamed.renameTo(newFile);
//		if(fileResult){
//			logger.debug("文件已改成备份文件");
//			//重定向到后台登录地址
//		}
	}

}
