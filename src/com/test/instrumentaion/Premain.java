package com.test.instrumentaion;

import java.lang.instrument.Instrumentation;

public class Premain {

	/**
	 * 
	 * @param agentArgs premain�����õ��ĳ����������ͬ�C javaagentһ���롣
	 * @param inst
	 */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Hello, I'm instrmentation agent");
        inst.addTransformer(new Transformer()); 
    }
}
