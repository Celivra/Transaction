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
function chat(productId, otherUserId){
    const form = document.createElement("form");
    form.method = "POST";
    form.action = "/chat";   // 对应你的 @PostMapping("/chat")

    const p = document.createElement("input");
    p.type = "hidden";
    p.name = "productId";
    p.value = productId;

    const u = document.createElement("input");
    u.type = "hidden";
    u.name = "otherUserId";
    u.value = otherUserId;

    form.appendChild(p);
    form.appendChild(u);

    document.body.appendChild(form);
    form.submit();

}