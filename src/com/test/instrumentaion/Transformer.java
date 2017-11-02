package com.test.instrumentaion;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class Transformer implements ClassFileTransformer {
	 
	   @Override
	   public byte[] transform(ClassLoader l, String className, Class<?> c, 
	           ProtectionDomain pd, byte[] b) throws IllegalClassFormatException { 
		   ClassReader cr = new ClassReader(b);
	        ClassNode cn = new ClassNode();
	        cr.accept(cn, 0);
	        for (Object obj : cn.methods) {
	            MethodNode md = (MethodNode) obj;
	            if ("<init>".endsWith(md.name) || "<clinit>".equals(md.name)) {
	                continue;
	            }
	            InsnList insns = md.instructions;
	            InsnList il = new InsnList();
	            il.add(new FieldInsnNode(Opcodes.GETSTATIC, "java/lang/System",
	                    "out", "Ljava/io/PrintStream;"));
	            il.add(new LdcInsnNode("Enter method-> " + cn.name+"."+md.name));
	            il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V"));
	            insns.insert(il);
	            md.maxStack += 3;

	        }
	        ClassWriter cw = new ClassWriter(0);
	        cn.accept(cw);
	        return cw.toByteArray();
	 
	   } 

}
