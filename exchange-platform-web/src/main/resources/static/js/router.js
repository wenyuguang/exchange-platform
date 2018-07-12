	avalon.config({debug: false})
	require(["domReady!", "mmState"], function() {
		// 定义一个顶层的vmodel，用来放置全局共享数据
		var root = avalon.define({
		    $id:'admin',
		});
		avalon.state("search", {
		    url: "/search",
		    views: {
		      "": {
		    	    templateUrl:"./pages/search/search.html", 
        			controllerUrl: "../../pages/search/search.js"
		      }
		    }
		  });
		avalon.state("organization", {
		    url: "/organization",
		    views: {
		      "": {
		    	    templateUrl:"./pages/search/organization.html", 
        			controllerUrl: "../../pages/search/organization.js"
		      }
		    }
		  });
		avalon.state("people", {
		    url: "/people",
		    views: {
		      "": {
		    	    templateUrl:"./pages/search/people.html", 
        			controllerUrl: "../../pages/search/people.js"
		      }
		    }
		  });
		// 全局配置
		avalon.state.config({
		  // 强烈打开错误配置
		  onError: function() {
		      console.log(arguments)
		  },
		  // 跳转成功
		  onLoad: function() {
		      root.page = mmState.currentState.stateName.split(".")[1]
		  }
		})

		avalon.history.start({})
		//默认打开home
		/* avalon.router.navigate("/home")  */
		avalon.scan()
    })    