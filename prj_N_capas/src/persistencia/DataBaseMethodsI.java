
package persistencia;

import java.util.Collection;
import recursos.GlobalException;
import recursos.NoDataExeption;

public interface DataBaseMethodsI {
    
    public Collection findAll() throws GlobalException, NoDataExeption;
    
    public void create(Object obj)throws GlobalException, NoDataExeption;
    
    public void delete(Object obj)throws GlobalException, NoDataExeption;
    
    public Object findOne(Object obj) throws GlobalException, NoDataExeption;
    
    public void update(Object obj)throws GlobalException, NoDataExeption;
    
}