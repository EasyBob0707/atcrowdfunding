package com.atguigu.atcrowdfunding.quertz;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时任务
 * 
 * @Author SUNBO
 * @Date 2017年7月22日 上午9:53:19
 * @Version V1.0
 */
public class FinishWorkTask {
	// 石英调度
	public void finish() {
		System.out.println("完成功能......");
	}

	// Java当中的定时任务
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("java中的定时任务执行......");
			}
		}, 5000, 3000);
	}
}
