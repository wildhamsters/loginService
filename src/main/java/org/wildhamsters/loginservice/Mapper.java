package org.wildhamsters.loginservice;

/**
 * @author Piotr Chowaniec
 */
interface Mapper<K, V> {

    V map(K key);
}
