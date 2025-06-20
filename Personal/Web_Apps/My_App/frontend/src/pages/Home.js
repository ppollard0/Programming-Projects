import { React } from "react";
import "../css/Home.css";

export default function Home() {

    return (
        <div className="root">
            <div className="header">
                <h1>Hello, Welcome to My App</h1>
            </div>
            <div className="body">
                <div>
                    <h3>Take a look around.</h3>
                </div>
                <div className="nav">
                    <a className="bButton" href="/api">API</a>
                    <a className="bButton" href="/fgbhjiosfnbpijsefrpnjbgsdf">Get Lost</a>
                </div>
            </div>
            <div className="footer">
                <p>© 2025 Paul Pollard. Not really copyrighted.</p>
            </div>
        </div>
    );
}