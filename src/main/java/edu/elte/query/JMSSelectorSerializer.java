package edu.elte.query;

/**
 *
 * @author Xavier
 */
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.primitives.Primitives;
import com.querydsl.codegen.Serializer;
import com.querydsl.collections.CollQueryFunctions;
import com.querydsl.collections.CollQueryTemplates;
import com.querydsl.core.QueryException;
import com.querydsl.core.support.SerializerBase;
import com.querydsl.core.types.*;

/**
 * {@code CollQuerySerializer} is a {@link Serializer} implementation for the Java language
 *
 * @author tiwe
 */





public class JMSSelectorSerializer extends SerializerBase<JMSSelectorSerializer> {


    private static final Set<Class<?>> WRAPPER_TYPES = ImmutableSet.copyOf(Primitives.allWrapperTypes());

    private static final Map<Operator, String> OPERATOR_SYMBOLS = Maps.newIdentityHashMap();

    private static final Map<Class<?>, String> CAST_SUFFIXES = Maps.newHashMap();
    
    private final Templates templates;

    private boolean strict = true;
     private static final Set<Operator> SAME_PRECEDENCE = ImmutableSet.<Operator>of(Ops.CASE,
        Ops.CASE_WHEN, Ops.CASE_ELSE, Ops.CASE_EQ, Ops.CASE_EQ_WHEN, Ops.CASE_EQ_ELSE);

    static {
        OPERATOR_SYMBOLS.put(Ops.EQ, " == ");
        OPERATOR_SYMBOLS.put(Ops.NE, " != ");
        OPERATOR_SYMBOLS.put(Ops.GT, " > ");
        OPERATOR_SYMBOLS.put(Ops.LT, " < ");
        OPERATOR_SYMBOLS.put(Ops.GOE, " >= ");
        OPERATOR_SYMBOLS.put(Ops.LOE, " <= ");

        OPERATOR_SYMBOLS.put(Ops.ADD, " + ");
        OPERATOR_SYMBOLS.put(Ops.SUB, " - ");
        OPERATOR_SYMBOLS.put(Ops.MULT, " * ");
        OPERATOR_SYMBOLS.put(Ops.DIV, " / ");

        CAST_SUFFIXES.put(Boolean.class, ".booleanValue()");
        CAST_SUFFIXES.put(Byte.class, ".byteValue()");
        CAST_SUFFIXES.put(Character.class, ".charValue()");
        CAST_SUFFIXES.put(Double.class, ".doubleValue()");
        CAST_SUFFIXES.put(Float.class, ".floatValue()");
        CAST_SUFFIXES.put(Integer.class, ".intValue()");
        CAST_SUFFIXES.put(Long.class, ".longValue()");
        CAST_SUFFIXES.put(Short.class, ".shortValue()");
        CAST_SUFFIXES.put(String.class, ".toString()");
    }
    public JMSSelectorSerializer(Templates templates) {
        super(templates);
        this.templates =templates;
    }

    @Override
    public Void visit(Path<?> path, Void context) {
        final PathType pathType = path.getMetadata().getPathType();
         if (pathType == PathType.PROPERTY) {
            final Path<?> parent = path.getMetadata().getParent();
            final String property = path.getMetadata().getName();
            final Class<?> parentType = parent.getType();
            try {
                // getter
                Method m = getAccessor(parentType, property);
                if (m != null && Modifier.isPublic(m.getModifiers())) {
                    handle(parent);
                    append(property);
                } 

            } catch (Exception e) {
                throw new QueryException(e);
            }
            
            
            
            
             } else if (pathType == PathType.DELEGATE) {
            append("(");
            append("(").append(path.getType().getName()).append(")");
            path.getMetadata().getParent().accept(this, context);
            append(")");

        } else {
//            List<Object> args = new ArrayList<>(2);
//            if (path.getMetadata().getParent() != null) {
//                args.add(path.getMetadata().getParent());
//            }
//            args.add(path.getMetadata().getElement());
//            final Template template = getTemplate(pathType);
//            for (Template.Element element : template.getElements()) {
//                Object rv = element.convert(args);
//                if (rv instanceof Expression) {
//                    ((Expression<?>) rv).accept(this, context);
//                } else if (element.isString()) {
//                    //TODO verify that removing alias does not affect the rest of execution
////                    append(rv.toString());
//                } else {
//                    visitConstant(rv);
//                }
//            }
        } 
         return null;
        
    }

    @Override
    protected void visitOperation(Class<?> type, Operator operator, List<? extends Expression<?>> args) {

        final Template template = templates.getTemplate(operator);
        if (template != null) {
            final int precedence = templates.getPrecedence(operator);
            boolean first = true;
            for (final Template.Element element : template.getElements()) {
                final Object rv = element.convert(args);
                if (rv instanceof Expression) {
                    final Expression<?> expr = (Expression<?>) rv;
                    if (precedence > -1 && expr instanceof Operation) {
                        Operator op = ((Operation<?>) expr).getOperator();
                        int opPrecedence = templates.getPrecedence(op);
                        if (precedence < opPrecedence) {
                            append("(").handle(expr).append(")");
                        } else if (!first && precedence == opPrecedence && !SAME_PRECEDENCE.contains(op)) {
                            append("(").handle(expr).append(")");
                        } else {
                            handle(expr);
                        }
                    } else {
                        handle(expr);
                    }
                    first = false;
                } else if (element.isString()) {
//                    append(rv.toString());
                    append(rv.toString().replace("[", "").replace("]", ""));
                } else {
                    visitConstant(rv);
                }
            }
        } else if (strict) {
            throw new IllegalArgumentException("No pattern found for " + operator);
        } else {
            append(operator.toString());
            append("(");
            handle(", ", args);
            append(")");
        }
        }
    
    
    
    
   
    
    
    
    @Override
    public void visitConstant(Object constant) {
        super.visitConstant(constant); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    @Override
    public Void visit(ParamExpression<?> expr, Void context) {
        return null;
    }

    @Override
    public Void visit(FactoryExpression<?> expr, Void context) {
         visitConstant(expr);
        append(".newInstance(");
        handle(", ", expr.getArgs());
        append(")");
        return null;
    }


    @Override
    public Void visit(SubQueryExpression<?> query, Void context) {
       throw new IllegalArgumentException("Not supported");
    }
    
    
    
        private Method getAccessor(Class<?> owner, String property) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(owner);
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : descriptors) {
                if (pd.getName().equals(property)) {
                    return pd.getReadMethod();
                }
            }
            return null;
        } catch (IntrospectionException e) {
            return null;
        }
    }

//    private Field getField(Class<?> owner, String field) {
//        try {
//            return owner.getField(field);
//        } catch (NoSuchFieldException e) {
//            return null;
//        }
//    }
    
}



//public final class JMSSelectorSerializer extends SerializerBase<JMSSelectorSerializer> {
//
//    private static final Set<Class<?>> WRAPPER_TYPES = ImmutableSet.copyOf(Primitives.allWrapperTypes());
//
//    private static final Map<Operator, String> OPERATOR_SYMBOLS = Maps.newIdentityHashMap();
//
//    private static final Map<Class<?>, String> CAST_SUFFIXES = Maps.newHashMap();
//
//    static {
//        OPERATOR_SYMBOLS.put(Ops.EQ, " == ");
//        OPERATOR_SYMBOLS.put(Ops.NE, " != ");
//        OPERATOR_SYMBOLS.put(Ops.GT, " > ");
//        OPERATOR_SYMBOLS.put(Ops.LT, " < ");
//        OPERATOR_SYMBOLS.put(Ops.GOE, " >= ");
//        OPERATOR_SYMBOLS.put(Ops.LOE, " <= ");
//
//        OPERATOR_SYMBOLS.put(Ops.ADD, " + ");
//        OPERATOR_SYMBOLS.put(Ops.SUB, " - ");
//        OPERATOR_SYMBOLS.put(Ops.MULT, " * ");
//        OPERATOR_SYMBOLS.put(Ops.DIV, " / ");
//
//        CAST_SUFFIXES.put(Boolean.class, ".booleanValue()");
//        CAST_SUFFIXES.put(Byte.class, ".byteValue()");
//        CAST_SUFFIXES.put(Character.class, ".charValue()");
//        CAST_SUFFIXES.put(Double.class, ".doubleValue()");
//        CAST_SUFFIXES.put(Float.class, ".floatValue()");
//        CAST_SUFFIXES.put(Integer.class, ".intValue()");
//        CAST_SUFFIXES.put(Long.class, ".longValue()");
//        CAST_SUFFIXES.put(Short.class, ".shortValue()");
//        CAST_SUFFIXES.put(String.class, ".toString()");
//    }
//
//    public JMSSelectorSerializer(JMSTemplates templates) {
//        super(templates);
//    }
//
////    @Override
////    public Void visit(Path<?> path, Void context) {
////        final PathType pathType = path.getMetadata().getPathType();
////        if (pathType == PathType.PROPERTY) {
////            final Path<?> parent = path.getMetadata().getParent();
////            final String property = path.getMetadata().getName();
////            final Class<?> parentType = parent.getType();
////            try {
////                // getter
////                Method m = getAccessor(parentType, property);
////                if (m != null && Modifier.isPublic(m.getModifiers())) {
////                    handle(parent);
////                    append(".").append(m.getName()).append("()");
////                } else {
////                    // field
////                    Field f = getField(parentType, property);
////                    if (f != null && Modifier.isPublic(f.getModifiers())) {
////                        handle(parent);
////                        append(".").append(property);
////                    } else {
////                        // field access by reflection
////                        append(CollQueryFunctions.class.getName() + ".<");
////                        append((path.getType()).getName()).append(">get(");
////                        handle(parent);
////                        append(", \"" + property + "\")");
////                    }
////                }
////            } catch (Exception e) {
////                throw new QueryException(e);
////            }
////
////        } else if (pathType == PathType.DELEGATE) {
////            append("(");
////            append("(").append(path.getType().getName()).append(")");
////            path.getMetadata().getParent().accept(this, context);
////            append(")");
////
////        } else {
////            List<Object> args = new ArrayList<Object>(2);
////            if (path.getMetadata().getParent() != null) {
////                args.add(path.getMetadata().getParent());
////            }
////            args.add(path.getMetadata().getElement());
////            final Template template = getTemplate(pathType);
////            for (Template.Element element : template.getElements()) {
////                Object rv = element.convert(args);
////                if (rv instanceof Expression) {
////                    ((Expression<?>) rv).accept(this, context);
////                } else if (element.isString()) {
////                    append(rv.toString());
////                } else {
////                    visitConstant(rv);
////                }
////            }
////        }
////        return null;
////
////    }
//
//    private Method getAccessor(Class<?> owner, String property) {
//        try {
//            BeanInfo beanInfo = Introspector.getBeanInfo(owner);
//            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
//            for (PropertyDescriptor pd : descriptors) {
//                if (pd.getName().equals(property)) {
//                    return pd.getReadMethod();
//                }
//            }
//            return null;
//        } catch (IntrospectionException e) {
//            return null;
//        }
//    }
//
//    private Field getField(Class<?> owner, String field) {
//        try {
//            return owner.getField(field);
//        } catch (NoSuchFieldException e) {
//            return null;
//        }
//    }
//
//    @Override
//    public Void visit(SubQueryExpression<?> expr, Void context) {
//        throw new IllegalArgumentException("Not supported");
//    }
//
//    private void visitCast(Operator operator, Expression<?> source, Class<?> targetType) {
//        if (Number.class.isAssignableFrom(source.getType()) && !Constant.class.isInstance(source)) {
//            append("new ").append(source.getType().getSimpleName()).append("(");
//            handle(source);
//            append(")");
//        } else {
//            handle(source);
//        }
//
//        if (CAST_SUFFIXES.containsKey(targetType)) {
//            append(CAST_SUFFIXES.get(targetType));
//        } else {
//            throw new IllegalArgumentException("Unsupported cast type " + targetType.getName());
//        }
//    }
//
//    @Override
//    protected void visitOperation(Class<?> type, Operator operator, List<? extends Expression<?>> args) {
//        if (Ops.aggOps.contains(operator)) {
//            throw new UnsupportedOperationException("Aggregation operators are only supported as single expressions");
//        }
//        if (args.size() == 2 && OPERATOR_SYMBOLS.containsKey(operator)
//             && isPrimitive(args.get(0).getType()) && isPrimitive(args.get(1).getType())) {
//            handle(args.get(0));
//            append(OPERATOR_SYMBOLS.get(operator));
//            handle(args.get(1));
//            if (args.get(1) instanceof Constant<?>) {
//                append(CAST_SUFFIXES.get(args.get(1).getType()));
//            }
//            return;
//        }
//
//        if (operator == Ops.STRING_CAST) {
//            visitCast(operator, args.get(0), String.class);
//        } else if (operator == Ops.NUMCAST) {
//            @SuppressWarnings("unchecked") //this is the second argument's type
//            Constant<Class<?>> rightArg = (Constant<Class<?>>) args.get(1);
//
//            visitCast(operator, args.get(0), rightArg.getConstant());
//        } else {
//            super.visitOperation(type, operator, args);
//        }
//    }
//
//    private static boolean isPrimitive(Class<?> type) {
//        return type.isPrimitive() || WRAPPER_TYPES.contains(type);
//    }
//
//    @Override
//    public Void visit(FactoryExpression<?> expr, Void context) {
//        visitConstant(expr);
//        append(".newInstance(");
//        handle(", ", expr.getArgs());
//        append(")");
//        return null;
//    }
//
//}

