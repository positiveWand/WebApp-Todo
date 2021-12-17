function init() {
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
        popupAppear(overlayContainer, "Todo 추가하기", "추가하기");
        document.getElementById("create_time").parentElement.style.display = "none";
        document.getElementById("status").setAttribute("value", "TODO");
        document.getElementById("status").disabled = true;
    }, false);
    closeFormButton.addEventListener("click", function(event) {
        popupDisapppear(overlayContainer);
    }, false);

    for(let i = 0; i < items.length; i++) {
        items[i].parentNode.addEventListener("click", function(event) {
            popupAppear(overlayContainer, "Todo 수정하기", "수정하기");
            document.getElementById("create_time").disabled = true;
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

init();