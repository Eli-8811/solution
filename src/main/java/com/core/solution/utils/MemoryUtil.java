package com.core.solution.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemoryUtil {
	
    public static void showMemoryStats() {    	
        int mb = 1024*1024;
        Runtime runtime = Runtime.getRuntime();        
        log.info("##### Heap utilization statistics [MB] start #####");   
        log.info("Used Memory {} " + (runtime.totalMemory() - runtime.freeMemory()) / mb);        
        log.info("Free Memory {} " + runtime.freeMemory() / mb);        
        log.info("Total Memory {} " + runtime.totalMemory() / mb);        
        log.info("Max Memory {} " + runtime.maxMemory() / mb);
        log.info("##### Heap utilization statistics [MB] finish #####");
    }
    
    public static long tiempoInicial(){
        return System.currentTimeMillis();
    }
    
    public static long tiempoFinal(long tiempoInicial){
        long tiempoFinal = System.currentTimeMillis();
        return tiempoFinal - tiempoInicial;
    }
    
}
