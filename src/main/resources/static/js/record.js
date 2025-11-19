function switchTab(tab) {
    document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
    document.querySelectorAll('.records').forEach(r => r.classList.remove('active'));

    document.querySelector(`.tab[onclick="switchTab('${tab}')"]`).classList.add('active');
    document.getElementById(tab + '-records').classList.add('active');
}

function shipNow(recordId) {
    if (confirm("确认要发货吗？")) {
        fetch(`/shipNow?recordId=${recordId}`, {
            method: 'POST'
        }).then(res => {
            if (res.ok) {
                alert("发货成功！");
                location.reload();
            } else {
                alert("发货失败，请稍后再试。");
            }
        });
    }
}
function deleteNow(recordId){
    if(confirm("确认要删除吗？")){
        fetch(`/deleteNow?recordId=${recordId}`,{
            method:'POST'
        }).then(res=>{
            if (res.ok){
                alert("删除成功");
                location.reload();
            }else{
                alert("删除失败");
            }
        })
    }
}
