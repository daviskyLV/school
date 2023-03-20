//MAKE THE MAGIC HAPPEN
//Mikkel's code
let apples = [];
// Format [inbasket?, id, left, bottom]

function appleStart() {
    $(".apple").each(function () {
        let apple = $(this);
        let left = Math.floor(Math.random() * 500) + 50;
        let bottom = Math.floor(Math.random() * 250) + 400;

        apples.push([false, apple.attr("id"), left, bottom])

        let tree = $("#tree").position();
        apple.css("left", tree.left + left);
        apple.css("bottom", bottom);
    });

    $("#basketfront").css("z-index", 30);
    $(".apple").css("z-index", 29);
}

$(window).on("resize", function () {
    apples.forEach(function (apple) {
        if (apple[0] != true) {
            $("#"+apple[1]).css("left", $("#tree").position().left + apple[2]);
        } else {
            $("#"+apple[1]).css("left", $(".basket").position().left + apple[2]);
        }
    })
});

$(".apple").on("click", function() {
    let left = $(this).attr("id").slice(-1)*40-5;
    console.log(left);
    let bottom = 70;

    let basket = $(".basket").position();
    $(this).animate({left: basket.left+left, bottom: bottom});

    apples[$(this).attr("id").slice(-1)-1] = [true, $(this).attr("id"), left, bottom];
});

appleStart();

function skyStart() {
    $("#moon").show();
    $("#sun").hide();
}

$("body").mouseleave(function () {
    $("#moon").fadeIn();
    $("#sun").fadeOut();
    $("#filter").animate({opacity: "50%"});
});

$("body").mouseenter(function () {
    $("#moon").fadeOut();
    $("#sun").fadeIn();
    $("#filter").animate({opacity: "0%"});
});

skyStart();
// Mikkel's code-end

//Andy :P
$("#waterdrop1").hide()
$("#waterdrop2").hide()
$("#waterdrop3").hide()


let lastMouseX = 0;
$(document).mousemove(function(e) {
    if (lastMouseX-e.pageX < 0) {
        $("#net").css({"webkit-transform": "scaleX(-1)",
                            transform: "scaleX(-1)",
                            left: e.pageX-125,
                            top: e.pageY-75});
    }
    else if(lastMouseX-e.pageX > 0) {
        $("#net").css({"webkit-transform": "scaleX(1)",
                            transform: "scaleX(1)",
                            left: e.pageX-125,
                            top: e.pageY-75});
    }
    lastMouseX = e.pageX;
});


var watering = false;
$("#wateringcan").click(function () {
    if (watering) { // setting back to non watering
        document.querySelector("#wateringcan").style.transform = "rotate(0deg)";
        $("#waterdrop1").hide();
        $("#waterdrop2").hide();
        $("#waterdrop3").hide();
    } else { // setting to watering position
        document.querySelector("#wateringcan").style.transform = "rotate(300deg)";
        $("#waterdrop1").show();
        $("#waterdrop2").show();
        $("#waterdrop3").show();

        setTimeout(function() { // so it loops
            waterLoop($("#waterdrop1"));
        }, 0);
        setTimeout(function() {
            waterLoop($("#waterdrop2"));
        }, 140);
        setTimeout(function() {
            waterLoop($("#waterdrop3"));
        }, 280);
    }
    watering = !watering;
});

// Andy & Andrejs Umbrovskis collab
function waterLoop(droplet) { // droplet = $("#waterdropX")
    if (watering) {
        droplet.animate({bottom: "20%"}, 0, "linear");
        droplet.animate({ bottom:"-50px" },420,'linear');
        setTimeout(function() {
            waterLoop(droplet);
        }, 420);
    }
}
// collab end

// Konami code secret
var allowedKeys = {
    37: 'left',
    38: 'up',
    39: 'right',
    40: 'down',
    65: 'a',
    66: 'b'
};
  
  
  var konamiCode = ['up', 'up', 'down', 'down', 'left', 'right', 'left', 'right', 'b', 'a'];
  
  var konamiCodePosition = 0;
  

  document.addEventListener('keydown', function(e) {
    
    var key = allowedKeys[e.keyCode];
    
    var requiredKey = konamiCode[konamiCodePosition];
  
    
    if (key == requiredKey) {
  
      
      konamiCodePosition++;
  
      
      if (konamiCodePosition == konamiCode.length) {
        activateCheats();
        konamiCodePosition = 0;
      }
    } else {
      konamiCodePosition = 0;
    }
  });
  
  function activateCheats() {
    var imigis = document.getElementsByTagName("img");
for (var i = 0; i < imigis.length; i++) {
  imigis[i].style.display = 'none';        
}
    document.body.style.backgroundImage = "url('images/Kasper.jpg')";
  
    var audio = new Audio('images/Castlevania.mp3');
    audio.play();
  
    alert("The Hero Of Legend....Kasper Knop Rasmussen....an honour to face you");
  }

//Andy :P-end

// [ANDREJS DAVIS UMBROVSKIS]
Math.clamp = function(a,b,c) { // value, min, max
    return Math.max(b,Math.min(c,a));
}

let butterfly = $("#butterfly");
butterfly.css({"z-index":"100","filter":"drop-shadow(0px 0px 10px green)"});

let butterflyFades = 0;
function fadeButterfly() { // Halloween isnt over, right? (extra feature)
    let newAnimation = butterflyFades<=0 ? true : false;
    butterflyFades = 3; //resetting butterfly fades count to 3
    
    if (newAnimation) {
        function goBack() {
            if (butterflyFades >0) {
                butterflyFades--;
                butterfly.fadeOut(150).delay(150).fadeIn(150);
                setTimeout(function() {
                    goBack();
                }, 300);
            }
        }
        goBack();
    }
}

butterfly.hover(function() { // moves butterfly to a random location
    let screenSize = [$(window).width(),$(window).height()];
    let butterflySize = [butterfly.width(), butterfly.width()];

    let newX = (screenSize[0]-butterflySize[0])*Math.random();
    let newY = (screenSize[1]-butterflySize[1])*Math.random();

    butterfly.offset({left: newX, top: newY});
    fadeButterfly();
});

function butterflyFlies() { // random butterfly movement
    let screenSize = [$(window).width(),$(window).height()];
    let butterflySize = [butterfly.width(), butterfly.width()];
    let butterflyPos = butterfly.position();

    let newX = butterflyPos.left-6+12*Math.random();
    let newY = butterflyPos.top-6+12*Math.random();

    newX = Math.clamp(newX,0,(screenSize[0]-butterflySize[0]))
    newY = Math.clamp(newY,0,(screenSize[1]-butterflySize[1]))

    butterfly.offset({left: newX,top: newY});

    setTimeout(butterflyFlies,125);
}
butterflyFlies();
// ANDREJS DAVIS UMBROVSKIS-end