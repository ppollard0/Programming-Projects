// JS code for the home page of Plantgram.
//
// Author: Paul Pollard Created 11/10/2021
//
// Modificarions
//

var homePage;
$(()=> {
    homePage = new App('PlantGram');
    homePage.initialize();
});

class App {
    constructor (name) {
        this.title = name;
    }

    getTitle() {
        return this.title;
    }

    initialize () {
        // code for listeners on the home page
        $("accountSignUp").modal('show');
        console.log("Initialized Page");

        $("#rePass").on("change", (evt) => {
            let passMatch = this.matchPasswords();
            if (!this.matchPasswords() && $("#wrongPass").hasClass("d-none")) {
              $("#wrongPass").removeClass("d-none");
            }
            else if (this.matchPasswords() && (!$("#wrongPass").hasClass("d-none"))){
              $("#wrongPass").addClass("d-none");
            }
        });

        $("#signUpForm").on("submit", (evt)=> {
            evt.preventDefault();

            let data = {
                fname: $("#fSignUp").val(),
                lname: $("#lSignUp").val(),
                email: $("#emailSU").val(),
                pass: $("#passWrd").val(),
                sUAbout: $("#sUAbout").val()
            };
            $.ajax({
                url: "/api/users",
                type: "POST",
                data: JSON.stringify(data),
                contentType: "application/json",
                dataType: "JSON"
            })
            .done((data, textStatus, xhr)=>{
                if (xhr.status == 201) {
                  console.log(`Data msg is: ${data.msg}`);
                  $("#accountSignUp").modal("hide");
                }
                else 
                  console.log(`Error Code: ${textStatus}`);
                return false;
            });
        });

        $("#signInForm").on("submit", (evt)=> {
            evt.preventDefault();

            let data = {
                eMail: $("#eMail").val(),
                passWrd: $("#pass").val()
            };
            $.ajax({
                url: "/api/auth",
                type: "POST",
                data: JSON.stringify(data),
                contentType: "application/json",
                dataType: "JSON"
            })
            .done((data, textStatus, xhr)=>{
                if (xhr.status == 200) {
                  console.log(`Data msg is: ${data.msg}`);
                  $("#accountLogin").modal("hide");
                  window.location.replace("http://localhost:3000/pages/catalog.html")
                }
                else 
                  console.log(`Error Code: ${textStatus}`);
                return false;
            });
        });
    }

    matchPasswords(){
        let pass = $("#passWrd").val();
        let repass = $("#rePass").val();
        if (pass === repass && (pass !== null || repass !== null))
           return true;
        else
           return false;
    }
}