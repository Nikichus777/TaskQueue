package kz.iskst.dao;

import java.util.List;

public abstract class Enricher<T> {
    
    public abstract void enrich(T record);
    
    public void enrichAll(List<T> records){
	
	for (T rec : records){
	    enrich(rec);
	}
	
    }
    
}
