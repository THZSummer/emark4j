package com.mlj.emark4j.core.module;

/**
 * 2023/01/14：bind A(Action) to V(View)
 *
 * @author MLJ
 */
public interface EBinder<A, V> {
    void binder(A a, V v);
}
