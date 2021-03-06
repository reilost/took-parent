var permissionModule = {
	init:function(){
		$('#dg').datagrid({
			url:ctx+'/system/permission/permissionList',
			fit:true,
	        singleSelect: true,  
	        rownumbers:true,
	        toolbar:"#toolbar",
	        columns:[[
						{field:'id',hidden:true},
						{field:'permissionName',title:'权限名称',align:'center',width:200},
						{field:'permissionIdentify',title:'权限标识',align:'center',width:200},
						{field:'remark',title:'备注',align:'center',width:200}
	                ]],
            onLoadError : function(data) {
	  			this.datagrid('loadData', {
	  				total : 0,
	  				rows : []
	  			})
            },
            onBeforeLoad:function(param){
	  			//设置查询条件
	  		    param.permissionName=$('#permissionName-search').val();
	  		},
  			loadFilter:function(data){
	        	return {
	        		total:data.totalCount,
	        		rows:data.data
	        	}
	        },
	        pageSize: 20,         
	        pageList: [20,40,60,80],
	        pagination : true
		});
	},
	search:function(){
		$('#dg').datagrid('reload');
	},
	add:function(){
		permissionModule.clearForm();
		$('#formDialog').dialog({title: '新增', iconCls:'icon-add'});
		$('#formDialog').dialog('open');
		
		//保存按钮注册click事件
		$('#save').off('click').on('click',function(){
			$('#form').form('submit', {    
    		    url:ctx+'/system/permission/addPermission', 
    		    onSubmit: function(param){    
    		    	return  $(this).form('validate');  		    	
    		    },    
    		    success:function(dataStr){
    		    	var data = JSON.parse(dataStr);
    		    	if(data.success){   		    		
    		    		$('#formDialog').dialog('close');
    		    		//重新加载列表
    		    		$('#dg').datagrid('reload');	
    		    		messager.show("添加成功");
    		    	}else{
    		    		$.messager.alert('提示',data.message); 
    		    	}
    		    }    
    		});
		});
	},
	modify:function(){
		var row = $('#dg').datagrid('getSelected');
		if(!row){
			messager.show("请选择一条记录!");
			return;
		}
		
		 permissionModule.clearForm();
		 $('#formDialog').dialog({title: '修改',iconCls:'icon-edit'});
		 //载入数据到表单
		 $('#form').form('load',row);
		 $('#formDialog').dialog('open');
		 
		//保存按钮注册click事件
		$('#save').off('click').on('click',function(){
			$('#form').form('submit', {    
    		    url:ctx+'/system/permission/modifyPermission', 
    		    onSubmit: function(param){    
    		    	return  $(this).form('validate');  		    	
    		    },    
    		    success:function(dataStr){
    		    	var data = JSON.parse(dataStr);
    		    	if(data.success){   		    		
    		    		$('#formDialog').dialog('close');
    		    		//重新加载列表
    		    		$('#dg').datagrid('reload');	
    		    		messager.show("修改成功");
    		    	}else{
    		    		$.messager.alert('提示',data.message); 
    		    	}
    		    }    
    		});
		});
	},
	del:function(){
		var row = $('#dg').datagrid('getSelected');
		if(!row){
			messager.show("请选择一条记录!");
			return;
		}
		
		$.messager.confirm("提示", "您确定删除此权限吗？",
				function(r) {
			 		if(r){
			 			$.ajax({  
				            type : "POST",  
				            url : ctx+"/system/permission/delPermission/"+row.id,
				            dataType: "json",
				            success : function(result) {
				                if (result.success) {  
				                	messager.show("删除权限成功");
				                	//重新加载列表
			    		    		$('#dg').datagrid('reload');			                	
				                } else {  
				                	messager.alert(result.message); 
				                }  
				            },
				            error:function(){
				            	messager.alert("网络异常"); 
				            }
				        }); 
			 		}
				});
	},
	 //清除表单
	 clearForm:function(){
		$('#form').form('clear');
	 }
}

$(function(){
	permissionModule.init();
	$('#search').click(permissionModule.search);
	$('#add').click(permissionModule.add);
	$('#modify').click(permissionModule.modify);
	$('#del').click(permissionModule.del);
	
	//解决页面加载时，出现未渲染的对话框内容
	$('#formDialog').css('visibility','visible');
});