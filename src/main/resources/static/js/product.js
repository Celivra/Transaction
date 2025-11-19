function takeOffProduct(productId){
    if(confirm("确定要下架吗？")){
        fetch(`/TakeOffProduct?productId=${productId}`,{
            method:'POST'
        }).then(res=>{
            if(res.ok){
                alert("下架成功");
            }else{
                alert("下架失败");
            }
        })
    }
}
function removeProduct(productId){
    if(confirm("确定要删除吗？")){
        fetch(`/removeProduct?productId=${productId}`,{
            method:'POST'
        }).then(res=>{
            if(res.ok){
                alert("删除成功");
            }else{
                alert("删除失败");
            }
        })
    }
}