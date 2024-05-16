import axios from "axios";
import * as SecureStore from "expo-secure-store"

export function LoadJournal(num) {
    let data = JSON.stringify({
    "num": num
    });

    let config = {
    method: 'get',
    url: 'http://10.15.3.139:3000/api/data/load_journal?',
    headers: { 
        'Content-Type': 'application/json'
    },
    data : data
    };

    axios
        .request(config)
        .then((response) => {
            console.log(JSON.stringify(response.data));
        })
        .catch((error) => {
            console.log(error);
        });



    /*let data = JSON.stringify({
        num: num
      });
    
    let config = {
      get: {
        method: 'get',
        url: 'http://10.15.3.139:3000/api/data/load_journal',
        headers: { 
          'Content-Type': 'application/json'
        },
        data
      },
    };
    console.log(data)
    axios
        .request(config.get)
        .then((response) => {
            const respond = response.data;
            return(respond)
        })
        .catch((Error)=> {
            console.log(Error)
        });*/
}

export default LoadJournal