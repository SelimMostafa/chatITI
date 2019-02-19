/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatserver.model.DAOInteraface;

import java.util.Map;

/**
 *
 * @author HP
 */
public interface UserStatisticsDAOInterface {

    public int countOffline();

    public int countOnline();

    public int countFemale();

    public int countMale();
    
    public  Map<String, Integer> groupByCountry();

    public Map<String, Integer> getEntryTimes();
}
