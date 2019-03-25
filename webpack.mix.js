let mix = require('laravel-mix');
let tailwindcss = require('tailwindcss');

mix.options({
    processCssUrls: false
});

mix.js('frontend/session/app.js', 'src/main/webapp/js/session')
    .version();

mix.js('frontend/token/app.js', 'src/main/webapp/js/token')
    .version();

mix.sass('frontend/css/app.scss', 'src/main/webapp/css')
    .options({
        postCss: [
            tailwindcss('./tailwind-config.js'),
        ]
    })
    .version();

