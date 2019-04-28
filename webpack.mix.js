let mix = require('laravel-mix');
let tailwindcss = require('tailwindcss');

mix.options({
    processCssUrls: false
});

mix.js('frontend/app.js', 'src/main/webapp/js')
    .version();

mix.sass('frontend/css/app.scss', 'src/main/webapp/css')
    .options({
        postCss: [
            tailwindcss('./tailwind-config.js'),
        ]
    })
    .version();

