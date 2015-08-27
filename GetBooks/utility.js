var select_urls = {};

var onCheck = function(element){

	var top = document.getElementById("urls");
	
	select_urls[element.className]='';
	
	if(element.checked) {
		//console.log(element.className);
		var elements = document.getElementsByClassName(element.className);
		//console.log(elements.length);
			
		if(elements.length===2) {
			for(var i = 0;i<elements.length;++i) {
				if(elements[i].nodeName==='DIV'){
					select_urls[element.className] += elements[i].innerHTML;
/* 					var urls = elements[i].getElementsByTagName('a');
					for(var j = 0;j<urls.length;++j) {						
						//console.log(urls[j].attributes.href.value);
						//divTop.innerHTML += urls[j].innerHTML;
						
					} */
				}
			}
		}		
	}
	else {
		select_urls[element.className]='';
	}
	
	top.innerHTML = '';
	for (var key in select_urls) {
		if (select_urls.hasOwnProperty(key)) {
			top.innerHTML += select_urls[key]; 
		}
	}
	

}


