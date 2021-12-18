let todoTable = Object();
let tableReady = false;
let maxID = 1000;

function init() {
    getTable().then(data => {
        todoTable = data;
        tableReady = true;
        maxID = getMaxID(todoTable);
    });

    // Item 드래그 앤 드롭 기능
    let dragged;
    let draggedItemID;

    let container = document.getElementById("container-top");

    container.addEventListener("drag", function(event) {}, false);
    container.addEventListener("dragstart", function(event) {
        dragged = event.target;
        let draggedItem = dragged.firstChild;
        while(draggedItem.className != "todo_item") {
            draggedItem = draggedItem.nextSibling;
        }
        draggedItemID = draggedItem.id;
    }, false);
    container.addEventListener("dragend", function(event) {
        event.target.style.opacity = "";
    }, false);
    container.addEventListener("dragover", function(event) {
        event.preventDefault();
        if(event.target.className != "todo_item_blank" && event.target.className != "todo_item_blank last_blank") {
            let blankList = document.getElementsByClassName("todo_item_blank");
            for(let i = 0; i < blankList.length; i++) {
                blankList[i].style.background = "";
                blankList[i].style.transition = "";
                blankList[i].style.height = "";
                blankList[i].style.borderRadius = "";
                blankList[i].style.marginTop = ""
                blankList[i].style.marginBottom = "";
            }
        }
    }, false);

    container.addEventListener("dragenter", function(event) {
        //console.log(event.target.className);
        if((event.target.className == "todo_item" || event.target.className == "todo_item_blank last_blank") && event.target.id != draggedItemID) {
            if(event.target.className == "todo_item_blank last_blank") {
                event.target.style.backgroundColor = "powderblue";
                event.target.style.transition = "background-color .35s";
                event.target.style.height = "6.5rem";
                event.target.style.borderRadius = "1rem";
                event.target.style.marginTop = "0.75rem"
                event.target.style.marginBottom = "0.75rem";

            } else {
                let targetPreviousBlank = event.target.parentNode;
                while(targetPreviousBlank.className != "todo_item_blank") {
                    targetPreviousBlank = targetPreviousBlank.previousSibling;
                }

                targetPreviousBlank.style.backgroundColor = "powderblue";
                targetPreviousBlank.style.transition = "background-color .35s";
                targetPreviousBlank.style.height = "6.5rem";
                targetPreviousBlank.style.borderRadius = "1rem";
                targetPreviousBlank.style.marginTop = "0.75rem"
                targetPreviousBlank.style.marginBottom = "0.75rem";
            }
            
            
           /*
            event.target.style.backgroundColor = "powderblue";
            event.target.style.transition = "background-color .35s";
            event.target.style.height = "6.5rem";
            event.target.style.borderRadius = "1rem";
            event.target.style.marginTop = "0.75rem"
            event.target.style.marginBottom = "0.75rem";
            */
            
        }
    }, false);
    container.addEventListener("dragleave", function(event) {
        if(event.target.className == "todo_item_blank" || event.target.className == "todo_item_blank last_blank") {
            event.target.style.background = "";
            event.target.style.transition = "";
            event.target.style.height = "";
            event.target.style.borderRadius = "";
            event.target.style.marginTop = ""
            event.target.style.marginBottom = "";
        }
    }, false);
    container.addEventListener("drop", function(event) {
        event.preventDefault();
        if(event.target.className == "todo_item_blank" || event.target.className == "todo_item_blank last_blank") {
            //event.target.style.background = "black";

            let targetElement = event.target;
            let newBlankElement = document.createElement("li");
            newBlankElement.setAttribute("class", "todo_item_blank");

            let targetPreviousBlank = dragged;
            while(targetPreviousBlank.className != "todo_item_blank") {
                targetPreviousBlank = targetPreviousBlank.previousSibling;
            }

            targetElement.parentNode.insertBefore(newBlankElement, targetElement);
            targetElement.parentNode.insertBefore(dragged, targetElement);

            targetPreviousBlank.remove();

            targetElement.style.background = "";
            targetElement.style.transition = "";
            event.target.style.height = "";
            event.target.style.borderRadius = "";
            event.target.style.marginTop = ""
            event.target.style.marginBottom = "";
        }
    }, false);


    let items = document.getElementsByClassName("todo_item");

    for(let i = 0; i < items.length; i++) {
        items[i].parentNode.setAttribute("draggable", "true");
    }


    // "Item 팝업창" 진입, 탈출 기능
    let addFormButton = document.getElementById("addFormAppearButton");
    let closeFormButton = document.getElementById("addFormCloseButton");
    let overlayContainer = document.getElementById("overlay-container");
    let shadeBox = document.getElementById("shade_box");

    // "Item 팝업창" 진입
    addFormButton.addEventListener("click", function(event) {
        setPopup("", "", "TODO", "");
        document.getElementById("create_time").parentElement.style.display = "none";
        document.getElementById("status").options[0].selected = true;
        document.getElementById("status").disabled = true;
        popupAppear(overlayContainer, "Todo 추가하기", "추가하기");
    }, false);
    closeFormButton.addEventListener("click", function(event) {
        popupDisapppear(overlayContainer);
    }, false);

    for(let i = 0; i < items.length; i++) {
        items[i].parentNode.addEventListener("click", function(event) {
            popupAppear(overlayContainer, "Todo 수정하기", "수정하기");
            document.getElementById("create_time").disabled = true;
            let itemID = Number(items[i].id.replace("item-", ""));
            let itemStatus = findItem(itemID)[0];
            let clickedItem = findItem(itemID)[1];
            setPopup(clickedItem.title, clickedItem.createTime, itemStatus, clickedItem.description);
        }, false);
    }

    // "Item 팝업창" 탈출
    shadeBox.addEventListener("click", function(event) {
        popupDisapppear(overlayContainer);
    }, false);
    document.addEventListener("keydown", function(event) {
        if(event.key == "Escape") {
            popupDisapppear(overlayContainer);
        }
    });


    // 팝업창 버튼 클릭 시
    let submitButton = document.getElementById("submit");

    submitButton.addEventListener("click", function(event) {
        // 양식 검사
        let pf = checkForm();

        if(pf && tableReady) { // 검사 통과 시
            if(document.getElementById("popupTitle").innerHTML == "Todo 추가하기") {
                // 새로운 item 추가
                let newItem = {
                    id : maxID + 1,
                    title : document.getElementById("title").value,
                    description : document.getElementById("description").value,
                    create_time : modifyTimeA(document.getElementById("time").value),
                };
                addItem("todo", newItem);
                updateTable();
            } else {
                // 기존 item 수정

            }
        } else if(!pf) { // 검사 탈락 시
            window.alert("제목은 반드시 있어야합니다!");
        } else {
            window.alert("로딩 중입니다.");
        }
    });
}

async function getTable() {
    let theResponse = await fetch("http://localhost:8080/getTable", {method : "POST"});
    let theObject = await theResponse.json();

    return theObject;
}
function addItem(listType, newItem) {
    // 데이터 갱신
    if(listType == "todo") {
        todoTable.todo.push(newItem);
    } else if(listType == "doing") {
        todoTable.doing.push(newItem);
    } else if(listType == "done") {
        todoTable.done.push(newItem);
    } else {

    }
    // View에 추가
    let newListElement = document.createElement("li");
    let newDiv = document.createElement("div");
    let newTitleNode = document.createElement("h3");
    let newTitleText = document.createTextNode(newItem.title);
    let newDateNode = document.createElement("p");
    let newDateText = document.createTextNode(newItem.create_date);

    newDiv.className = "todo_item";
    newDiv.id = "item" + newItem.id;

    newDateNode.appendChild(newDateText);
    newTitleNode.appendChild(newTitleText);
    newDiv.appendChild(newTitleNode);
    newDiv.appendChild(newDateNode);
    newListElement.appendChild(newDiv);

    document.querySelector("ul#todo_list").insertBefore(newListElement, document.querySelector("ul#todo_list>.last_blank"));

}

function popupAppear(popupElement, popupTitle, popupSubmit) {
    document.getElementById("popupTitle").innerHTML = popupTitle;
    document.getElementById("submit").setAttribute("value", popupSubmit);



    popupElement.style.visibility = "visible";
}
function popupDisapppear(popupElement) {
    popupElement.style.visibility = "hidden";

    document.getElementById("create_time").parentElement.style.display = "";
    document.getElementById("status").setAttribute("value", "TODO");
    document.getElementById("status").disabled = false;
}
function setPopup(title, create_time, status, description) {
    document.getElementById("title").value = title;
    document.getElementById("create_time").value = modifyTimeB(create_time);
    switch (status) {
        case "todo":
            document.getElementById("status").options[0].selected = true;
            break;
        case "doing":
            document.getElementById("status").options[1].selected = true;
            break;
        case "done":
            document.getElementById("status").options[2].selected = true;
            break;
        default:
            break;
    }
    document.getElementById("description").innerHTML = description;
}

function updateTable() {
    // 데이터 서버에 전송
    
    // 화면 전환
    popupDisapppear();
}

function checkForm() {
    if(document.getElementById("title").value == "") {
        return false;
    }
    return true;
}

function getMaxID(tableObject) {
    let maxNum = 0;

    for(let i = 0; i < tableObject.todo.length; i++) {
        if(tableObject.todo[i].id > maxNum) {
            maxNum = tableObject.todo[i].id
        }
    }
    for(let i = 0; i < tableObject.doing.length; i++) {
        if(tableObject.doing[i].id > maxNum) {
            maxNum = tableObject.doing[i].id
        }
    }
    for(let i = 0; i < tableObject.done.length; i++) {
        if(tableObject.done[i].id > maxNum) {
            maxNum = tableObject.done[i].id
        }
    }

    return maxNum;
}
function modifyTimeA(timeString) {
    let newTimeString = timeString;

    newTimeString = newTimeString.replace("T", " ") + ":00.0";

    return newTimeString;
}
function modifyTimeB(timeString) {
    let newTimeString = timeString;

    newTimeString = newTimeString.slice(0, 10) + "T" + newTimeString.slice(11, 16);

    return newTimeString;
}

function findItem(targetID) {
    let tableObject = todoTable;
    let targetItem = null;

    for(let i = 0; i < tableObject.todo.length; i++) {
        if(tableObject.todo[i].id == targetID) {
            return ["todo", tableObject.todo[i]];
        }
    }
    for(let i = 0; i < tableObject.doing.length; i++) {
        if(tableObject.doing[i].id == targetID) {
            return ["doing", tableObject.doing[i]];
        }
    }
    for(let i = 0; i < tableObject.done.length; i++) {
        if(tableObject.done[i].id == targetID) {
            return ["done", tableObject.done[i]];
        }
    }
}

init();