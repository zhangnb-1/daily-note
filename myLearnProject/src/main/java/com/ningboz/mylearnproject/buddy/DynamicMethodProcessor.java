package com.ningboz.mylearnproject.buddy;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.Writer;
import java.util.Set;

@SupportedAnnotationTypes("DynamicMethod")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class DynamicMethodProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(annotation);
            for (Element element : elements) {
                DynamicMethod dm = element.getAnnotation(DynamicMethod.class);
                String className = element.getSimpleName() + "Stub"; // 存根类名

                try (Writer writer = processingEnv.getFiler().createSourceFile(className).openWriter()) {
                    writer.write("public interface " + className + " {\n");
                    writer.write("    " + dm.returnType().getSimpleName() + " " + dm.methodName() + "();\n");
                    writer.write("}\n");
                } catch (Exception e) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.getMessage());
                }
            }
        }
        return true;
    }
}