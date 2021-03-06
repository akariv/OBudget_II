package com.yossale.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.smartgwt.client.data.XJSONDataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DragDataAction;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.tree.TreeGrid;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class OBudget2 implements EntryPoint {
	
	public void onModuleLoad() {

		TreeGrid localXmlFile = new TreeGrid();
		localXmlFile.setHeight(300);
		localXmlFile.setWidth(500);
		localXmlFile.setDataSource(DataSourceTest.getInstance());
		localXmlFile.setAutoFetchData(true);
		localXmlFile.setCanDragRecordsOut(true);
		localXmlFile.setDragDataAction(DragDataAction.MOVE);  
		
		
		XJSONDataSource yedaDS = new XJSONDataSource();
		yedaDS.setDataURL("http://api.yeda.us/data/gov/mof/budget/?o=jsonp&query=%7B%22code%22%20:%20%7B%20%22$regex%22%20:%20%22%5E0020%22%20%7D%7D");
        
        DataSourceTextField code = new DataSourceTextField("code","Code");
        code.setPrimaryKey(true);
        
        yedaDS.setTitleField("title");
        
        DataSourceIntegerField year = new DataSourceIntegerField("year", "Year");
        DataSourceTextField netAllocation = new DataSourceTextField("title","Title");
        
        yedaDS.setFields(code,year,netAllocation);
        
		TreeGrid remoteJsonQuery = new TreeGrid();

		remoteJsonQuery.setHeight(300);
		remoteJsonQuery.setWidth(500);
		remoteJsonQuery.setDataSource(yedaDS);
		remoteJsonQuery.setAutoFetchData(true);
		remoteJsonQuery.setDragDataAction(DragDataAction.MOVE);
		remoteJsonQuery.setCanAcceptDroppedRecords(true);
		
		HStack stack = new HStack();
		stack.addMember(localXmlFile);
		stack.addMember(remoteJsonQuery);

		VStack vStack = new VStack();
		vStack.addMember(new Label(" "));
		vStack.addMember(new Label("Label - 1"));
		vStack.addMember(stack);
		
		vStack.draw();
	}

}
