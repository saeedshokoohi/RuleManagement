package domain;

import org.primefaces.model.SelectableDataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saeed on 31/12/2015.
 */
public class RmRawRuleList  extends ArrayList<RmRawRule> implements SelectableDataModel {


    @Override
    public Object getRowKey(Object o) {
        return null;
    }

    @Override
    public Object getRowData(String s) {
        return null;
    }
}
