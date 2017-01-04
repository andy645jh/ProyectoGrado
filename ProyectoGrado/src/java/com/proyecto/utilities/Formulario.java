
package com.proyecto.utilities;

import java.util.List;
import javax.faces.model.SelectItem;


public class Formulario
{
    public static SelectItem[] addObject(List<?> objectsList, String textFirstChoice)
    {
        int index, size;
        SelectItem[] itemsList;
        
        index = 0;
        size = objectsList.size();
        if (textFirstChoice != null && !textFirstChoice.isEmpty()){
            size = size + 1;
            index = index + 1;
            itemsList = new SelectItem[size];
            itemsList[0] = new SelectItem("", textFirstChoice);
            
        }else{
            itemsList = new SelectItem[size];
        }
        
        for(Object obj: objectsList){
            
            itemsList[index] = new SelectItem(obj, obj.toString());
            index++;
        }
        return itemsList;
    } 
}
