package Service;


import Data.IData;

import java.util.List;
import java.util.Map;

/**
 * Created by ivaa on 10/6/2015.
 */
public interface Service
{
    public List<IData> getData(Map<String, String> params);
}
