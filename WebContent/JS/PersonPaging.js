/**
 * 本js文件中有意思的小技巧
 * 1. 使组件的标题居中: <center>titleName</center>
 * 2. 格式化date, 好像后台传递过来的参数必须是格式化的, 要不然JsonStore里解析不了
 */

Ext.onReady(function() {
	var pageSize = 5;
    
    var ds = new Ext.data.JsonStore({
    	root:'data',
    	totalProperty:
    	'totalCount',
    	fields: [{
    		name: 'id',
    		type: 'int'
    	}, {
    		name: 'name',
    		type: 'string'
    	}, {
    		name: 'birth',
    		type: 'date',
    		dateFormat: 'Y-m-d H:i:s'
    	}],
    	url: 'ext'
    });

    var cm = new Ext.grid.ColumnModel([
         new Ext.grid.RowNumberer(),	// 序号
        { header: '序号', dataIndex: 'id',width:15 },
        { header: '姓名', dataIndex: 'name',width:30 },
        { 
        	header: '生日', 
        	dataIndex: 'birth',
        	width:55 ,
        	sortable: true,  
  			renderer: Ext.util.Format.dateRenderer('Y-m-d H:i:s') 
  		}
    ]);
    cm.defaultSortable = true;
    ds.load({params:{start:0,limit:pageSize}});
    
    var grid = new Ext.grid.GridPanel({
		title:'<center>人员信息</center>',		// GridPanel的标题位于中间
        loadMask : {msg:'正在加载数据,请稍等......'},
        store: ds,
        layout:'fit',
        viewConfig: {
            forceFit: true
        },
        cm: cm,
        height:300,
        renderTo:Ext.getBody(),
        bbar: new Ext.PagingToolbar({
            pageSize: pageSize,
            store: ds,
            displayInfo: true,
            displayMsg: '当前显示{0} - {1}条，共{2}条数据',
            emptyMsg: "没有记录"
        }), 
        listeners: {
        	afterRender: function(grid) {
        		// 因为由pagingToolbar，所以每次默认传递参数start，limit
        		var jsonStore = grid.getStore();
        	}
        }
    });
});