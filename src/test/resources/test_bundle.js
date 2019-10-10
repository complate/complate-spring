function render(view, params, stream) {
    if (view === 'StaticView') {
        stream.write('Render view: ' + view);
    } else if (view === 'ModelView') {
        stream.write('View ' + view + ' with params.title=' + params.title)
    } else if (view === 'BindingView') {
        stream.write('View ' + view + ' with functionBinding.greet=' + functionBinding.greet(constantBinding));
    } else {
        stream.write("View not found: " + view);
    }
}
