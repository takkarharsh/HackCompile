package com.guavus.raf.compile.utils;

import org.joor.ReflectException;
import scala.Tuple2;
import shapeless.Tuple;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Compile {

    public static Tuple2<String,Boolean> compile(String className, String content) {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
//        lookup.lookupClass().getClassLoader().loadClass()

        Writer output = new StringWriter();
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector
                <javax.tools.JavaFileObject> diagnostics = new DiagnosticCollector<javax.tools.JavaFileObject>();
        try {
            Compile.ClassFileManager fileManager = new Compile.ClassFileManager(compiler.getStandardFileManager(null, null, null));

            List<Compile.CharSequenceJavaFileObject> files = new ArrayList<Compile.CharSequenceJavaFileObject>();
            files.add(new Compile.CharSequenceJavaFileObject(className, content));
//            String CLASSPATH = System.getProperty("java.class.path");
//            CLASSPATH = CLASSPATH+":/Users/harsh.takkar/gitRepo/charter_hunt/augmentedCompile/target/lib/*.jar";

            System.out.println(System.getProperty("loader.path"));
            boolean status = compiler.getTask(output, fileManager, null, Arrays.asList("-g", "-proc:none", "-classpath", System.getProperty("loader.path")), null, files).call();
//            for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
//                System.out.println(diagnostic.getCode());
//                System.out.println(diagnostic.getKind());
//                System.out.println(diagnostic.getPosition());
//                System.out.println(diagnostic.getStartPosition());
//                System.out.println(diagnostic.getEndPosition());
//                System.out.println(diagnostic.getSource());
//                System.out.println(diagnostic.getMessage(null));
//
//            }
            return new Tuple2<>(output.toString(), status);
        } catch (Exception e) {
            throw new ReflectException("Error while compiling " + className, e);
        }
    }

    static final class JavaFileObject extends SimpleJavaFileObject {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();

        JavaFileObject(String name, Compile.JavaFileObject.Kind kind) {
            super(URI.create("string:///" + name.replace('.', '/') + kind.extension), kind);
        }

        byte[] getBytes() {
            return os.toByteArray();
        }

        @Override
        public OutputStream openOutputStream() {
            return os;
        }
    }

    static final class ClassFileManager extends ForwardingJavaFileManager<StandardJavaFileManager> {
        Compile.JavaFileObject o;

        ClassFileManager(StandardJavaFileManager standardManager) {
            super(standardManager);
        }

        @Override
        public Compile.JavaFileObject getJavaFileForOutput(
                JavaFileManager.Location location,
                String className,
                Compile.JavaFileObject.Kind kind,
                FileObject sibling
        ) {
            return o = new Compile.JavaFileObject(className, kind);
        }
    }

    static final class CharSequenceJavaFileObject extends SimpleJavaFileObject {
        final CharSequence content;

        public CharSequenceJavaFileObject(String className, CharSequence content) {
            super(URI.create("string:///" + className.replace('.', '/') + Compile.JavaFileObject.Kind.SOURCE.extension), Compile.JavaFileObject.Kind.SOURCE);
            this.content = content;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return content;
        }
    }
}
