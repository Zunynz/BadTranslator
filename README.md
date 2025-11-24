# BadTranslator
A program that pushes your text through multiple languages in Google Translate, breaking its meaning.  It works via the console: you enter the text, number of iterations, and an array of languages.  Does not use the official Google Translate API — translations are performed through Google Apps Script for free.


To use translator you need to create a script in Google Apps Script.
1. Go to [Apps Script](https://developers.google.com/apps-script/) and login to your google account.
2. Click on "Start scripting" and create a new Project.
3. Copy and paste this script into your project.

```
var mock = {
  parameter:{
    q:'hello',
    source:'en',
    target:'fr'
  }
};


function doGet(e) {
  e = e || mock;

  var sourceText = ''
  if (e.parameter.q){
    sourceText = e.parameter.q;
  }

  var sourceLang = '';
  if (e.parameter.source){
    sourceLang = e.parameter.source;
  }

  var targetLang = 'en';
  if (e.parameter.target){
    targetLang = e.parameter.target;
  }

  var translatedText = LanguageApp.translate(sourceText, sourceLang, targetLang, {contentType: 'html'});

  return ContentService.createTextOutput(translatedText).setMimeType(ContentService.MimeType.JSON);
}
```

4. Click on "Deploy".
<img width="394" height="235" alt="изображение" src="https://github.com/user-attachments/assets/ddb02342-9c00-44b0-83ee-2b1c549aff73" />

5. Select "Web app" in "Select type".
<img width="387" height="240" alt="изображение" src="https://github.com/user-attachments/assets/806ad73a-488a-4413-8668-f8d1b8c1791e" />

6. Then you need to select "Execute as (your email)" and "Who has access" ― "Anyone".
7. Click on "Deploy" and copy your "Web app url".
8. Copy and paste it into the "SCRIPT_URL" constant.
