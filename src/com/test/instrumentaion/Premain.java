package com.test.instrumentaion;

import java.lang.instrument.Instrumentation;

public class Premain {

	/**
	 * 
	 * @param agentArgs premain函数得到的程序参数，随同– javaagent一起传入。
	 * @param inst
	 */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Hello, I'm instrmentation agent");
        inst.addTransformer(new Transformer()); 
    }
}
